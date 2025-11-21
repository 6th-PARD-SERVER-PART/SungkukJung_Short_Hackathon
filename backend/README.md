# Clean Go Backend API

집안 청소 루틴 관리 애플리케이션의 백엔드 API 서버입니다.

## 기술 스택

- **Framework**: Spring Boot 4.0.0
- **Language**: Java 17
- **Database**: MySQL 8.0
- **ORM**: Hibernate 7.1.8 (JPA)
- **Build Tool**: Gradle 9.2.1
- **Mapper**: MapStruct

### 주요 의존성

- Spring Data JPA
- Spring Web
- Lombok
- MySQL Connector
- MapStruct

---

## 프로젝트 구조

```
backend/
├── src/main/java/com/backend/
│   ├── controllers/          # REST API 컨트롤러
│   │   ├── PlaceController.java
│   │   ├── RoutineController.java
│   │   └── SupplyController.java
│   ├── services/             # 비즈니스 로직
│   │   ├── PlaceService.java
│   │   ├── RoutineService.java
│   │   └── SupplyService.java
│   ├── repositories/         # JPA 레포지토리
│   │   ├── PlaceRepository.java
│   │   ├── RoutineRepository.java
│   │   └── SupplyRepository.java
│   ├── entities/             # JPA 엔티티
│   │   ├── Place.java
│   │   ├── Routine.java
│   │   └── Supply.java
│   ├── dtos/                 # 데이터 전송 객체
│   │   ├── PlaceDto.java
│   │   ├── RoutineDto.java
│   │   └── SupplyDto.java
│   ├── mappers/              # MapStruct 매퍼
│   │   ├── PlaceMapper.java
│   │   ├── RoutineMapper.java
│   │   └── SupplyMapper.java
│   └── config/               # 설정 파일
│       └── CorsConfig.java
└── src/main/resources/
    ├── static/images/        # 정적 이미지 파일
    │   ├── places/           # 장소 이미지
    │   └── routines/         # 루틴 이미지
    └── application.yaml      # 애플리케이션 설정
```

---

## 시작하기

### 사전 요구사항

- Java 17 이상
- MySQL 8.0
- Gradle 9.2.1 (또는 포함된 Gradle Wrapper 사용)

### 1. 데이터베이스 설정

MySQL 데이터베이스를 생성합니다:

```sql
CREATE DATABASE clean_go CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 애플리케이션 설정

`src/main/resources/application.yaml` 파일에서 데이터베이스 연결 정보를 확인/수정합니다:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/clean_go?serverTimezone=UTC&characterEncoding=UTF-8
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
```

### 3. 애플리케이션 실행

```bash
# Gradle Wrapper를 사용한 실행
./gradlew bootRun

# 또는 빌드 후 실행
./gradlew build
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

애플리케이션은 기본적으로 `http://localhost:8080`에서 실행됩니다.

---

## API 엔드포인트

### 장소 (Place) API

#### 1. 모든 장소 조회
```
GET /places
```

**응답 예시:**
```json
[
  {
    "placeId": 1,
    "placeName": "거실",
    "placeImage": "/images/places/거실.jpg"
  },
  {
    "placeId": 2,
    "placeName": "주방",
    "placeImage": "/images/places/주방.jpg"
  }
]
```

#### 2. 특정 장소 상세 조회
```
GET /places/{id}
```

**응답 예시:**
```json
{
  "placeId": 1,
  "placeName": "거실",
  "routines": [
    {
      "routineId": 1,
      "orderIndex": 1,
      "title": "청소 준비 세팅 (2분)",
      "description": "쓰레기봉투를 손목에 걸거나...",
      "routineImage": "/images/routines/1단계.jpg",
      "isComplete": false
    }
  ],
  "supplies": [
    {
      "supplyId": 1,
      "supplyName": "쓰레기봉투"
    }
  ]
}
```

### 루틴 (Routine) API

#### 특정 장소의 루틴 목록 조회
```
GET /places/{placeId}/routines
```

**응답 예시:**
```json
[
  {
    "routineId": 1,
    "orderIndex": 1,
    "title": "청소 준비 세팅 (2분)",
    "description": "쓰레기봉투를 손목에 걸거나...",
    "routineImage": "/images/routines/1단계.jpg",
    "isComplete": false
  }
]
```

> **참고:** 루틴은 `orderIndex` 순서로 자동 정렬됩니다.

### 물품 (Supply) API

#### 특정 장소의 필요 물품 조회
```
GET /places/{placeId}/supplies
```

**응답 예시:**
```json
[
  {
    "supplyId": 1,
    "supplyName": "쓰레기봉투"
  },
  {
    "supplyId": 2,
    "supplyName": "마른 극세사"
  }
]
```

### 이미지 리소스

#### 장소 이미지
```
GET /images/places/{filename}
```

#### 루틴 이미지
```
GET /images/routines/{filename}
```

---

## 데이터베이스 스키마

### ER 다이어그램 관계

```
Place (1) ----< (N) Routine
Place (N) ----< (N) Supply
```

### 테이블 구조

#### Place (장소)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| place_id | LONG | 기본키 (자동 증가) |
| place_name | STRING | 장소 이름 |
| place_image | STRING | 이미지 URL |

#### Routine (루틴)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| routine_id | LONG | 기본키 (자동 증가) |
| order_index | LONG | 순서 번호 |
| title | STRING | 루틴 제목 |
| description | STRING | 루틴 설명 |
| routine_image | STRING | 이미지 URL |
| is_complete | BOOLEAN | 완료 여부 |
| place_id | LONG | 외래키 (Place) |

#### Supply (물품)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| supply_id | LONG | 기본키 (자동 증가) |
| supply_name | STRING | 물품 이름 |

#### Place_Supply (다대다 관계 테이블)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| place_id | LONG | 외래키 (Place) |
| supply_id | LONG | 외래키 (Supply) |

---

## 이미지 관리

### 이미지 저장 위치

이미지는 로컬 파일 시스템에 저장됩니다:

```
backend/
└── src/main/resources/static/images/
    ├── places/       # 장소 이미지 (거실.jpg, 주방.jpg 등)
    └── routines/     # 루틴 이미지 (1단계.jpg ~ 10단계.jpg)
```

### 이미지 추가 방법

1. 해당 디렉토리에 이미지 파일을 직접 추가
2. 데이터베이스에 이미지 URL 저장 (예: `/images/places/거실.jpg`)
3. 애플리케이션 재시작 (필요시)

### 이미지 접근

이미지는 Spring Boot의 정적 리소스 서빙 기능을 통해 자동으로 제공됩니다:

```
http://localhost:8080/images/places/거실.jpg
http://localhost:8080/images/routines/1단계.jpg
```

---

## 주요 기능

- ✅ 장소별 청소 루틴 관리
- ✅ 순서가 보장된 루틴 단계 (orderIndex)
- ✅ 장소와 물품의 다대다 관계 지원
- ✅ 정적 이미지 리소스 서빙
- ✅ RESTful API 설계
- ✅ DTO 패턴을 통한 엔티티 노출 방지

---

