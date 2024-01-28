package me.cocahack.dlq.consumer

import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.aggregator.DefaultAggregatingMessageGroupProcessor
import org.springframework.integration.aggregator.MessageCountReleaseStrategy
import org.springframework.integration.config.AggregatorFactoryBean
import org.springframework.integration.dsl.GatewayProxySpec
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlowBuilder
import org.springframework.integration.store.MessageGroupStore
import org.springframework.integration.store.SimpleMessageStore
import org.springframework.messaging.MessageHandler
import java.util.function.Consumer


@Configuration
class StreamConsumerConfiguration {

  @Bean
  fun streamUserConsumerFlow(
    @Qualifier("aggregator") aggregator: MessageHandler,
    streamHandler: StreamHandler
  ): IntegrationFlow {
    val builder: IntegrationFlowBuilder =
      IntegrationFlow.from(Consumer::class.java) { gateway: GatewayProxySpec -> gateway.beanName("streamUserConsumer") }
    return builder.handle(streamHandler).get()
  }

  @Bean
  fun aggregator(messageGroupStore: MessageGroupStore): FactoryBean<MessageHandler> {
    val aggregatorFactoryBean = AggregatorFactoryBean()
    aggregatorFactoryBean.setCorrelationStrategy { message -> message.payload.javaClass.name }
    aggregatorFactoryBean.setReleaseStrategy(MessageCountReleaseStrategy(BATCH_SIZE))
    aggregatorFactoryBean.setMessageStore(messageGroupStore)
    aggregatorFactoryBean.setProcessorBean(DefaultAggregatingMessageGroupProcessor())
    aggregatorFactoryBean.setExpireGroupsUponCompletion(true)
    aggregatorFactoryBean.setSendPartialResultOnExpiry(true)
    return aggregatorFactoryBean
  }

  @Bean
  fun messageGroupStore(): MessageGroupStore {
    val messageGroupStore = SimpleMessageStore()
    messageGroupStore.isTimeoutOnIdle = true
    messageGroupStore.setCopyOnGet(false)
    return messageGroupStore
  }

  companion object {
    const val BATCH_SIZE = 1
  }
}
