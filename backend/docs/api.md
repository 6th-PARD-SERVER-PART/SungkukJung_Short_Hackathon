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
}
```

---

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
}
```

---

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
}
```

---

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