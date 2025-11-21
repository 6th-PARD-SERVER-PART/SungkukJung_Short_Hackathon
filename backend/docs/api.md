<<<<<<< HEAD
# Cleaning Guide Backend API Specification (조회 전용 버전)

본 문서는 실제 구현된 기능만을 기반으로 하여, **데이터 생성 없이 조회(read-only) 중심으로 사용하는 경우**에 맞추어 재작성된 API 명세서입니다.

즉, 이 앱은 장소(Place), 준비물(Supply), 루틴(Routine)의 데이터를 미리 DB에 저장해두고,
**클라이언트는 이 데이터를 조회해서 보여주기만 하는 구조**입니다.

---

# 1. Base URL

`/api`

---

# 2. Place API (장소 조회 기능)

## 2.1 전체 장소 조회

**GET** `/api/place`

### Response

```json
[
  {
    "placeId": 1,
    "placeName": "욕실",
    "placeImage": "bathroom.png",
    "supplyId": 10,
    "routineId": 3
  }
]
```

---

## 2.2 장소 상세 조회

**GET** `/api/place/{placeId}`

### Response

```json
{
  "placeId": 1,
  "placeName": "욕실",
  "placeImage": "bathroom.png",
  "supplyId": 10,
  "routineId": 3
=======
# API 명세서

## 기본 정보
- Base URL: `https://api.example.com/v1`
- Content-Type: `application/json`

---

## User API

### 1. 사용자 생성
**POST** `/users`

사용자를 생성합니다.

**Request Body**
```json
{
  "name": "홍길동",
  "group_token": "abc123xyz"
}
```

**Response** (201 Created)
```json
{
  "id": 1,
  "name": "홍길동",
  "group_token": "abc123xyz",
  "created_at": "2025-11-21T10:30:00Z"
>>>>>>> origin/main
}
```

---

<<<<<<< HEAD
# 3. Supply API (준비물 조회 기능)

## 3.1 특정 장소의 준비물 목록 조회

**GET** `/api/supply/{placeId}`

### Response

```json
[
  {
    "supplyId": 1,
    "supplyName": "욕실 세정제",
    "placeId": 1
  }
]
```

---

## 3.2 단일 준비물 상세 조회

**GET** `/api/supply/detail/{supplyId}`

### Response

```json
{
  "supplyId": 1,
  "supplyName": "욕실 세정제",
  "placeId": 1
=======
### 2. 사용자 조회
**GET** `/users/{id}`

특정 사용자 정보를 조회합니다.

**Path Parameters**
- `id` (integer, required): 사용자 ID

**Response** (200 OK)
```json
{
  "id": 1,
  "name": "홍길동",
  "group_token": "abc123xyz",
  "group": {
    "token": "abc123xyz"
  }
>>>>>>> origin/main
}
```

---

<<<<<<< HEAD
# 4. Routine API (루틴 조회 기능)


## 4.1 특정 장소의 루틴 조회

**GET** `/api/routine/{placeId}`

### Response

```json
[
  {
    "routineId": 1,
    "orderIndex": 1,
    "title": "세면대 주변 정리",
    "description": "세면대 위 물건 정리",
    "routineImage": "bath_1.png",
    "isComplete": false,
    "placeId": 1
  }
]
```

---

## 4.2 개별 루틴 단계 조회

**GET** `/api/routine/detail/{routineId}`

### Response

```json
{
  "routineId": 1,
  "orderIndex": 1,
  "title": "세면대 주변 정리",
  "description": "세면대 위 물건 정리",
  "routineImage": "bath_1.png",
  "isComplete": false,
  "placeId": 1
=======
### 3. 사용자 목록 조회
**GET** `/users`

전체 사용자 목록을 조회합니다.

**Query Parameters**
- `group_token` (string, optional): 그룹 토큰으로 필터링
- `page` (integer, optional, default: 1): 페이지 번호
- `limit` (integer, optional, default: 20): 페이지당 항목 수

**Response** (200 OK)
```json
{
  "data": [
    {
      "id": 1,
      "name": "홍길동",
      "group_token": "abc123xyz"
    },
    {
      "id": 2,
      "name": "김철수",
      "group_token": "abc123xyz"
    }
  ],
  "pagination": {
    "page": 1,
    "limit": 20,
    "total": 2
  }
>>>>>>> origin/main
}
```

---
<<<<<<< HEAD
=======

### 4. 사용자 수정
**PUT** `/users/{id}`

사용자 정보를 수정합니다.

**Path Parameters**
- `id` (integer, required): 사용자 ID

**Request Body**
```json
{
  "name": "홍길동2",
  "group_token": "xyz456abc"
}
```

**Response** (200 OK)
```json
{
  "id": 1,
  "name": "홍길동2",
  "group_token": "xyz456abc",
  "updated_at": "2025-11-21T11:30:00Z"
}
```

---

### 5. 사용자 삭제
**DELETE** `/users/{id}`

사용자를 삭제합니다.

**Path Parameters**
- `id` (integer, required): 사용자 ID

**Response** (204 No Content)

---

## Group API

### 1. 그룹 생성
**POST** `/groups`

그룹을 생성합니다.

**Request Body**
```json
{
  "token": "abc123xyz"
}
```

**Response** (201 Created)
```json
{
  "token": "abc123xyz",
  "created_at": "2025-11-21T10:00:00Z"
}
```

---

### 2. 그룹 조회
**GET** `/groups/{token}`

특정 그룹 정보를 조회합니다.

**Path Parameters**
- `token` (string, required): 그룹 토큰

**Response** (200 OK)
```json
{
  "token": "abc123xyz",
  "user_count": 5
}
```

---

### 3. 그룹 목록 조회
**GET** `/groups`

전체 그룹 목록을 조회합니다.

**Query Parameters**
- `page` (integer, optional, default: 1): 페이지 번호
- `limit` (integer, optional, default: 20): 페이지당 항목 수

**Response** (200 OK)
```json
{
  "data": [
    {
      "token": "abc123xyz",
      "user_count": 5
    },
    {
      "token": "xyz456abc",
      "user_count": 3
    }
  ],
  "pagination": {
    "page": 1,
    "limit": 20,
    "total": 2
  }
}
```

---

### 4. 그룹의 사용자 목록 조회
**GET** `/groups/{token}/users`

특정 그룹에 속한 사용자 목록을 조회합니다.

**Path Parameters**
- `token` (string, required): 그룹 토큰

**Query Parameters**
- `page` (integer, optional, default: 1): 페이지 번호
- `limit` (integer, optional, default: 20): 페이지당 항목 수

**Response** (200 OK)
```json
{
  "group_token": "abc123xyz",
  "users": [
    {
      "id": 1,
      "name": "홍길동"
    },
    {
      "id": 2,
      "name": "김철수"
    }
  ],
  "pagination": {
    "page": 1,
    "limit": 20,
    "total": 2
  }
}
```

---

### 5. 그룹 삭제
**DELETE** `/groups/{token}`

그룹을 삭제합니다. (해당 그룹에 속한 사용자가 없어야 삭제 가능)

**Path Parameters**
- `token` (string, required): 그룹 토큰

**Response** (204 No Content)

**Error Response** (400 Bad Request)
```json
{
  "error": "GROUP_HAS_USERS",
  "message": "그룹에 속한 사용자가 존재하여 삭제할 수 없습니다."
}
```

---

## 공통 에러 응답

### 400 Bad Request
```json
{
  "error": "VALIDATION_ERROR",
  "message": "요청 데이터가 유효하지 않습니다.",
  "details": {
    "name": "이름은 필수 항목입니다."
  }
}
```

### 404 Not Found
```json
{
  "error": "NOT_FOUND",
  "message": "요청한 리소스를 찾을 수 없습니다."
}
```

### 500 Internal Server Error
```json
{
  "error": "INTERNAL_SERVER_ERROR",
  "message": "서버 내부 오류가 발생했습니다."
}
```
>>>>>>> origin/main
