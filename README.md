# 도메인 주도 개발 시작하기

## 환경 설정

1. docker run
```bash
 docker run --name mysql5.7.38 -e MYSQL_ROOT_PASSWORD=root -p 3308:3306 -d mysql:5.7.38 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

- 현재 `3306` 포트 점유 중이므로, `3307` 로 변경

2. ddl.sql 실행
3. init.sql 실행
4. application 실행
   1. server.port : 8081 변경
   2. datasource의 url 변경


## 정리

### annotation

- `@EmbeddedId`
  - [참고](https://1minute-before6pm.tistory.com/17)
  - 복합키 사용을 위한 어노테이션
- `@Embedded`
  - [참고1](https://www.baeldung.com/jpa-embedded-embeddable)
  - [참고2](https://galid1.tistory.com/592)
  - 클래스로 그 내용을 entity에 끼어넣기 위함.
  - Embed...들은 Entity의 가독성을 높히기 위함인듯 (복잡도를 낮추기 위함) 

### 읽으면서..

#### 1ch

- 일반적인 애플리케이션의 구조는 [표현 - 응용 - 도메인 - 인프라스트럭처]
  - 표현은 UI
  - 응용은 서비스, 뭔가 조합해서 처리하는 거
  - 도메인은 말그대로 도메인. 도메인에는 규칙이 있음.
  - 인프라스트럭처는 데이터베이스 혹은 외부시스템과의 연계
- 비즈니스 구현에 있어서, 중요한 규칙은 도메인에 구현되어야함.
  - 예를 들면 '배송 전에만 배송지를 변경할 수 있다' 라는 검증 로직은 도메인이 담당해야한다.
  - 왜?
  - 그게 도메인 주도..?
- 생성자에는 반드시 필요한 것을 넘긴다.
- 엔티티 식별자(id)를 가짐, 식별자로 구별함.
- 밸류는 식별자 대신 다른 걸 통해, 동일성을 판단.
- 유비쿼터스 언어. 전문가, 관계자, 개발자가 공통된 언어를 사용해서 소통과정에서의 모호함을 줄일 수 있도록..