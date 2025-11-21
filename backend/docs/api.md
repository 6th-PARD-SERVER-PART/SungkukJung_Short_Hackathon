# 📌 Cleaning Guide App API 명세서

**Base URL**: `/api`

---

## 1. Place API

**Base URL**: `/api/places`

### ERD

| 필드명 | 타입 | 설명 |
|--------|------|------|
| placeId | Long | PK |
| placeName | String | 장소명 |
| placeImage | String | 장소 대표 이미지 파일명 |
| supplyId | Long | FK → Supply.supplyId |
| routineId | Long | FK → Routine.routineId |

---

### 1) 전체 장소 조회

**GET** `/places`

#### 📌 설명
모든 장소(Place)를 리스트 형태로 조회합니다.

#### ✔ Response (200 OK)
```json
[
    {
        "placeId": 1,
        "placeName": "욕실",
        "placeImage": "bathroom.png",
        "supplyId": 10,
        "routineId": 100
    },
    {
        "placeId": 2,
        "placeName": "주방",
        "placeImage": "kitchen.png",
        "supplyId": 11,
        "routineId": 101
    }
]
```

---

### 2) 단일 장소 조회

**GET** `/places/{placeId}`

#### 📌 설명
placeId를 기반으로 특정 장소의 상세 정보를 조회합니다.

#### ✔ Path Variable

| 이름 | 타입 | 설명 |
|------|------|------|
| placeId | Long | 조회할 장소 ID |

#### ✔ Response (200 OK)
```json
{
    "placeId": 1,
    "placeName": "욕실",
    "placeImage": "bathroom.png",
    "supplyId": 10,
    "routineId": 100
}
```

#### ❗ Response (404 Not Found)
```json
{
    "error": "Place not found",
    "placeId": 999
}
```

---

## 2. Supply API

**Base URL**: `/api/supplies`

### ERD

| 필드명 | 타입 | 설명 |
|--------|------|------|
| supplyId | Long | PK |
| supplyName | String | 청소 도구/세제 이름 |
| placeId | Long | FK → Place.placeId |

---

### 1) 전체 청소용품 조회

**GET** `/supplies`

#### 📌 설명
모든 청소용품(Supply)을 리스트 형태로 조회합니다.

#### ✔ Response (200 OK)
```json
[
    {
        "supplyId": 10,
        "supplyName": "변기 세정제",
        "placeId": 1
    },
    {
        "supplyId": 11,
        "supplyName": "주방 세제",
        "placeId": 2
    }
]
```

---

### 2) 단일 청소용품 조회

**GET** `/supplies/{supplyId}`

#### 📌 설명
supplyId를 기반으로 특정 청소용품의 상세 정보를 조회합니다.

#### ✔ Path Variable

| 이름 | 타입 | 설명 |
|------|------|------|
| supplyId | Long | 조회할 청소용품 ID |

#### ✔ Response (200 OK)
```json
{
    "supplyId": 10,
    "supplyName": "변기 세정제",
    "placeId": 1
}
```

#### ❗ Response (404 Not Found)
```json
{
    "error": "Supply not found",
    "supplyId": 999
}
```

---

### 3) 특정 장소의 청소용품 조회

**GET** `/places/{placeId}/supplies`

#### 📌 설명
특정 장소에 필요한 청소용품 목록을 조회합니다.

#### ✔ Path Variable

| 이름 | 타입 | 설명 |
|------|------|------|
| placeId | Long | 장소 ID |

#### ✔ Response (200 OK)
```json
[
    {
        "supplyId": 10,
        "supplyName": "변기 세정제",
        "placeId": 1
    },
    {
        "supplyId": 12,
        "supplyName": "욕실 청소 솔",
        "placeId": 1
    }
]
```

---

## 3. Routine API

**Base URL**: `/api/routines`

### ERD

| 필드명 | 타입 | 설명 |
|--------|------|------|
| routineId | Long | PK |
| orderIndex | Long | 루틴 순서를 위한 정렬 기준(sortBy) |
| title | String | 루틴 단계 제목 |
| description | String | 루틴 설명 |
| routineImage | String | 루틴 단계 이미지 |
| isComplete | Boolean | 단계 완료 여부 |
| placeId | Long | FK → Place.placeId |

---

### 1) 전체 루틴 조회

**GET** `/routines`

#### 📌 설명
모든 루틴(Routine)을 리스트 형태로 조회합니다.

#### ✔ Query Parameters

| 이름 | 타입 | 필수 여부 | 설명 |
|------|------|----------|------|
| placeId | Long | Optional | 특정 장소의 루틴만 필터링 |
| sortBy | String | Optional | 정렬 기준 (기본값: orderIndex) |

#### ✔ Response (200 OK)
```json
[
    {
        "routineId": 100,
        "orderIndex": 1,
        "title": "변기 청소",
        "description": "변기 세정제를 뿌린 후 솔로 문지릅니다.",
        "routineImage": "toilet_cleaning.png",
        "isComplete": false,
        "placeId": 1
    },
    {
        "routineId": 101,
        "orderIndex": 2,
        "title": "싱크대 청소",
        "description": "주방 세제로 싱크대를 닦습니다.",
        "routineImage": "sink_cleaning.png",
        "isComplete": false,
        "placeId": 2
    }
]
```

---

### 2) 단일 루틴 조회

**GET** `/routines/{routineId}`

#### 📌 설명
routineId를 기반으로 특정 루틴의 상세 정보를 조회합니다.

#### ✔ Path Variable

| 이름 | 타입 | 설명 |
|------|------|------|
| routineId | Long | 조회할 루틴 ID |

#### ✔ Response (200 OK)
```json
{
    "routineId": 100,
    "orderIndex": 1,
    "title": "변기 청소",
    "description": "변기 세정제를 뿌린 후 솔로 문지릅니다.",
    "routineImage": "toilet_cleaning.png",
    "isComplete": false,
    "placeId": 1
}
```

#### ❗ Response (404 Not Found)
```json
{
    "error": "Routine not found",
    "routineId": 999
}
```

---

### 3) 특정 장소의 루틴 조회

**GET** `/places/{placeId}/routines`

#### 📌 설명
특정 장소의 청소 루틴 목록을 orderIndex 순서대로 조회합니다.

#### ✔ Path Variable

| 이름 | 타입 | 설명 |
|------|------|------|
| placeId | Long | 장소 ID |

#### ✔ Response (200 OK)
```json
[
    {
        "routineId": 100,
        "orderIndex": 1,
        "title": "변기 청소",
        "description": "변기 세정제를 뿌린 후 솔로 문지릅니다.",
        "routineImage": "toilet_cleaning.png",
        "isComplete": false,
        "placeId": 1
    },
    {
        "routineId": 102,
        "orderIndex": 2,
        "title": "욕조 청소",
        "description": "욕조 세정제로 욕조를 닦습니다.",
        "routineImage": "bathtub_cleaning.png",
        "isComplete": false,
        "placeId": 1
    }
]
```

---

### 4) 루틴 완료 상태 변경

**PATCH** `/routines/{routineId}/complete`

#### 📌 설명
특정 루틴의 완료 여부를 토글합니다.

#### ✔ Path Variable

| 이름 | 타입 | 설명 |
|------|------|------|
| routineId | Long | 루틴 ID |

#### ✔ Request Body
```json
{
    "isComplete": true
}
```

#### ✔ Response (200 OK)
```json
{
    "routineId": 100,
    "orderIndex": 1,
    "title": "변기 청소",
    "description": "변기 세정제를 뿌린 후 솔로 문지릅니다.",
    "routineImage": "toilet_cleaning.png",
    "isComplete": true,
    "placeId": 1
}
```

#### ❗ Response (404 Not Found)
```json
{
    "error": "Routine not found",
    "routineId": 999
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
        "field": "placeId",
        "issue": "placeId는 필수 항목입니다."
    }
}
```

### 500 Internal Server Error
```json
{
    "error": "INTERNAL_SERVER_ERROR",
    "message": "서버 내부 오류가 발생했습니다."
}
```