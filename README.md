# 환경 설정

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
