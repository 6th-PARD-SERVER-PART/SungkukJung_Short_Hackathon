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
}
```

---

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
}
```

---

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
}
```

---
