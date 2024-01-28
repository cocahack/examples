## Spring Integration & Kakfa Consumer & DLQ

카프카 컨슈머를 Spring Integration 으로 구현할 때, DLQ를 사용하는 방법에 대한 예제 코드입니다.

### 1. 인프라 준비

로컬 환경에 컨테이너 기반의 카프카를 쉽게 띄울 수 있도록 Confluent CLI를 사용.

```
brew install confluentinc/tap/cli

confluent local kafka start
confluent local kafka topic create quickstart
confluent local kafka topic produce quickstart
```

