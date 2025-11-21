# clean_go API Specification


---

# 1. API 개요 (Overview)

이 API는 clean_go 애플리케이션에서 사용하는 **조회 전용(Read-only) 백엔드 API**입니다.
DB에 사전 저장된 장소(Place), 루틴(Routine), 준비물(Supply)을 조회하여 클라이언트(iOS)에 제공합니다.

주요 기능:

* 장소 목록 조회
* 장소 상세 조회(루틴 + 준비물 포함)
* 특정 장소에 필요한 준비물 조회
* 특정 장소의 청소 루틴 조회

---

# 2. 엔드포인트 목록 (Endpoints)

| 구분      | Method | URL                          | 설명            |
| ------- | ------ | ---------------------------- | ------------- |
| Place   | GET    | `/places`                    | 장소 전체 조회      |
| Place   | GET    | `/places/{id}`               | 장소 상세 조회      |
| Supply  | GET    | `/places/{placeId}/supplies` | 특정 장소의 준비물 조회 |
| Routine | GET    | `/places/{placeId}/routines` | 특정 장소의 루틴 조회  |

---

# 3. API 상세 명세 (Details)

## 3.1 장소 전체 조회

### **GET `/places`**

### 📌 설명

등록된 모든 장소의 요약 정보를 반환합니다.

### 📌 매개변수 (Parameters)

없음

### 📌 응답 (Response 200)

```json
[
  {
    "placeId": 0,
    "placeName": "string",
    "placeImage": "string"
  }
]
```

### 📌 예제 요청

```
GET /places
```

---

## 3.2 장소 상세 조회

### **GET `/places/{id}`**

### 📌 설명

특정 장소의 상세 정보 및 해당 장소의 청소 루틴, 준비물을 함께 반환합니다.

### 📌 매개변수 (Path Parameter)

| 이름 | 타입    | 필수 | 설명        |
| -- | ----- | -- | --------- |
| id | int64 | ✔  | 조회할 장소 ID |


### 📌 응답 (Response 200)

```json
{
  "placeId": 0,
  "placeName": "string",
  "routines": [
    {
      "routineId": 0,
      "orderIndex": 0,
      "title": "string",
      "description": "string",
      "routineImage": "string",
      "isComplete": true
    }
  ],
  "supplies": [
    {
      "supplyId": 0,
      "supplyName": "string"
    }
  ]
}
```

### 📌 예제 요청

```
GET /places/1
```

---

## 3.3 특정 장소의 준비물 조회

### **GET `/places/{placeId}/supplies`**

### 📌 설명

특정 장소(placeId)에 필요한 모든 준비물을 조회합니다.

### 📌 매개변수 (Path Parameter)

| 이름      | 타입    | 필수 | 설명    |
| ------- | ----- | -- | ----- |
| placeId | int64 | ✔  | 장소 ID |


### 📌 응답 (Response 200)

```json
[
  {
    "supplyId": 0,
    "supplyName": "string"
  }
]
```

### 📌 예제 요청

```
GET /places/1/supplies
```

---

## 3.4 특정 장소의 루틴 조회

### **GET `/places/{placeId}/routines`**

### 📌 설명

특정 장소(placeId)의 청소 루틴을 순서(orderIndex) 기준으로 조회합니다.

### 📌 매개변수 (Path Parameter)

| 이름      | 타입    | 필수 |
| ------- | ----- | -- |
| placeId | int64 | ✔  |


### 📌 응답 (Response 200)

```json
[
  {
    "routineId": 0,
    "orderIndex": 0,
    "title": "string",
    "description": "string",
    "routineImage": "string",
    "isComplete": true
  }
]
```

### 📌 예제 요청

```
GET /places/1/routines
```

---

# 4. 자원(Resource) 모델 설명

## 📦 PlaceSummaryRequest

```json
{
  "placeId": 0,
  "placeName": "string",
  "placeImage": "string"
}
```

## 📦 SupplyDto

```json
{
  "supplyId": 0,
  "supplyName": "string"
}
```

## 📦 RoutineDto

```json
{
  "routineId": 0,
  "orderIndex": 0,
  "title": "string",
  "description": "string",
  "routineImage": "string",
  "isComplete": true
}
```

## 📦 PlaceDetailRequest

```json
{
  "placeId": 0,
  "placeName": "string",
  "routines": [ RoutineDto ],
  "supplies": [ SupplyDto ]
}
```

---
# 5. 엔티티(Entity) 구조 및 필드 설명


## 5.1 Place Entity

| 필드명        | 타입     | 설명      |
| ---------- | ------ | ------- |
| placeId    | Long   | PK      |
| placeName  | String | 장소 이름   |
| placeImage | String | 이미지 파일명 |

**관계**:

* Routine: placeId로 매핑
* Supply: placeId로 매핑

---

## 5.2 Routine Entity

| 필드명          | 타입      | 설명         |
| ------------ | ------- | ---------- |
| routineId    | Long    | PK         |
| orderIndex   | Long    | 루틴 정렬 순서   |
| title        | String  | 루틴 제목      |
| description  | String  | 설명         |
| routineImage | String  | 이미지 이름     |
| isComplete   | Boolean | 완료 여부      |
| placeId      | Long    | FK → Place |

---

## 5.3 Supply Entity

| 필드명        | 타입     | 설명         |
| ---------- | ------ | ---------- |
| supplyId   | Long   | PK         |
| supplyName | String | 준비물 이름     |
| placeId    | Long   | FK → Place |

---
