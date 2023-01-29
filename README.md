# peerfund 피펀

#### 단기 IT 프로젝트를 선호하는 사람들을 위한 플랫폼 매칭 서비스 ‘프리펀드’

## Tech Stack

- Kotlin
- SpringBoot
- Docker
- Thymeleaf
- QueryDSL
- Spring Data JPA
- Flyway
- Swagger-ui
- MockK
- Kotest

---
### 배포
1. 로컬 개발
2. 도커 이미지 생성
3. 도커 허브에 이미지 푸쉬
4. EC2서버 접속 후 도커 이미지 최신화 && 배포 스크립트 실행
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

