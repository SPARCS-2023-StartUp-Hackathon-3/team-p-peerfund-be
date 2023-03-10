# [TEAM P] peerfund 피펀

단기 IT 프로젝트를 선호하는 사람들을 위한 플랫폼 매칭 서비스 ‘프리펀드’
- 프로젝트/스터디 피어 구인
- 프로젝트/스터디 관리


## 프로젝트에서 사용한 기술

- Kotlin
- SpringBoot
- Docker
- AWS
- Thymeleaf
- QueryDSL
- Spring Data JPA
- Flyway
- Swagger-ui
- MockK
- Kotest

## Dev Server 실행 방법
1. 앱 실행

## Production 배포 방법

1. 로컬 개발 후 리모트 레포지토리 푸쉬
2. 도커 이미지 생성
3. 도커 허브에 이미지 푸쉬
4. EC2서버 접속 후 도커 이미지 최신화 && 배포 스크립트 실행
   <img width="800" alt="back" src="https://user-images.githubusercontent.com/42247724/215300408-690e059a-7547-41c0-99f0-ede3203940f7.png">
   <img width="800" alt="front" src="https://user-images.githubusercontent.com/42247724/215300536-c42da814-f76e-44cb-bec0-fa7b7ec9a3a5.png">

```shell
echo '[docker stop all]'
sudo docker stop $(sudo docker ps -a -q)

echo '[docker rm images]'
sudo docker rm $(sudo docker ps -a -q)

echo '[프론트 최신화]'
sudo docker pull peerfund/frontend:latest

echo '[프론트 배포]'
sudo docker run -d -p 80:80 peerfund/frontend:latest

echo '[백엔드 최신화]'
sudo docker pull peerfund/backend:latest

echo '[백엔드 배포]'
sudo docker run -p 8080:8080 peerfund/backend:latest
```


## 환경 변수 및 시크릿

spring:
    datasource:
    password:
aws:
    access-key:
    secret-key:
jwt-security:
    secret:

## 기타
- API
<img width="800" alt="api" src="https://user-images.githubusercontent.com/42247724/215300371-e3268195-6f58-4973-9d5f-31162a58034d.png">