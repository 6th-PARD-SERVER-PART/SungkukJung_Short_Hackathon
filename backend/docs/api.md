# Clean Go Backend API 명세서

## 기본 정보
- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json`
- **인코딩**: UTF-8

## Swagger 문서

API의 대화형 문서는 Swagger UI에서 확인할 수 있습니다:

**Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> localhost를 ip주소로 변경

Swagger UI를 통해 다음 기능을 사용할 수 있습니다:
- 모든 API 엔드포인트 확인
- 각 엔드포인트의 요청/응답 스키마 확인
- 브라우저에서 직접 API 테스트
- API 응답 실시간 확인

---

## Place (장소) API

### 1. 모든 장소 조회
**GET** `/places`

전체 장소 목록을 조회합니다.

**응답** (200 OK)
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
  },
  {
    "placeId": 3,
    "placeName": "화장실",
    "placeImage": "/images/places/화장실.jpg"
  },
  {
    "placeId": 4,
    "placeName": "침실",
    "placeImage": "/images/places/침실.jpg"
  }
]
```

---

### 2. 특정 장소 상세 조회
**GET** `/places/{id}`

특정 장소의 상세 정보를 조회합니다. 해당 장소의 루틴과 필요 물품 목록을 포함합니다.

**경로 파라미터**
- `id` (Long, 필수): 장소 ID

**응답** (200 OK)
```json
{
  "placeId": 1,
  "placeName": "거실",
  "routines": [
    {
      "routineId": 1,
      "orderIndex": 1,
      "title": "청소 준비 세팅 (2분)",
      "description": "쓰레기봉투를 손목에 걸거나 바지 주머니에 넣기; 물티슈/세정티슈 한 팩을 들고 다니기; 마른 극세사 1장 손에 들기; 청소기 전원 켜서 움직일 수 있는 곳에 두기; 정리 바구니(혹은 종이봉투)를 테이블 밑에 두기",
      "routineImage": "/images/routines/1단계.jpg",
      "isComplete": false
    },
    {
      "routineId": 2,
      "orderIndex": 2,
      "title": "시야를 뚫는 정리(5분)",
      "description": "테이블 위 물컵/책/영수증/화장품 등 전부 하나씩 손으로 쓸어 정리 바구니에 넣기; 소파 위 담요·옷은 접어서 한쪽에 두거나 각자 방 앞에 두기; 바닥에 놓인 택배 박스는 전부 한곳에 모으기; 리모컨, TV 패드, 게임패드는 TV 앞에 일렬 정렬",
      "routineImage": "/images/routines/2단계.jpg",
      "isComplete": false
    }
  ],
  "supplies": [
    {
      "supplyId": 1,
      "supplyName": "쓰레기봉투"
    },
    {
      "supplyId": 2,
      "supplyName": "마른 극세사"
    },
    {
      "supplyId": 3,
      "supplyName": "다용도 세정티슈"
    }
  ]
}
```

**에러 응답** (400 Bad Request)
```json
{
  "error": "Place not found"
}
```

---

## Routine (루틴) API

### 특정 장소의 루틴 목록 조회
**GET** `/places/{placeId}/routines`

특정 장소에 속한 루틴 목록을 순서대로 조회합니다.

**Path Parameters**
- `placeId` (Long, required): 장소 ID

**응답** (200 OK)
```json
[
  {
    "routineId": 1,
    "orderIndex": 1,
    "title": "청소 준비 세팅 (2분)",
    "description": "쓰레기봉투를 손목에 걸거나 바지 주머니에 넣기; 물티슈/세정티슈 한 팩을 들고 다니기; 마른 극세사 1장 손에 들기; 청소기 전원 켜서 움직일 수 있는 곳에 두기; 정리 바구니(혹은 종이봉투)를 테이블 밑에 두기",
    "routineImage": "/images/routines/1단계.jpg",
    "isComplete": false
  },
  {
    "routineId": 2,
    "orderIndex": 2,
    "title": "시야를 뚫는 정리(5분)",
    "description": "테이블 위 물컵/책/영수증/화장품 등 전부 하나씩 손으로 쓸어 정리 바구니에 넣기; 소파 위 담요·옷은 접어서 한쪽에 두거나 각자 방 앞에 두기; 바닥에 놓인 택배 박스는 전부 한곳에 모으기; 리모컨, TV 패드, 게임패드는 TV 앞에 일렬 정렬",
    "routineImage": "/images/routines/2단계.jpg",
    "isComplete": false
  },
  {
    "routineId": 3,
    "orderIndex": 3,
    "title": "쓰레기 1차 수거(3분)",
    "description": "테이블·소파·바닥에서 보이는 쓰레기 모두 쓰레기봉투로 바로 넣기; 작은 먼지나 과자 부스러기는 손으로 모아 휴지에 싸서 버리기",
    "routineImage": "/images/routines/3단계.jpg",
    "isComplete": false
  }
]
```

> **참고**: 루틴은 `orderIndex` 기준으로 오름차순 정렬되어 반환됩니다.

---

## Supply (물품) API

### 특정 장소의 필요 물품 조회
**GET** `/places/{placeId}/supplies`

특정 장소에서 필요한 물품 목록을 조회합니다.

**Path Parameters**
- `placeId` (Long, required): 장소 ID

**응답** (200 OK)
```json
[
  {
    "supplyId": 1,
    "supplyName": "쓰레기봉투"
  },
  {
    "supplyId": 2,
    "supplyName": "마른 극세사"
  },
  {
    "supplyId": 3,
    "supplyName": "다용도 세정티슈"
  },
  {
    "supplyId": 4,
    "supplyName": "정리 바구니"
  },
  {
    "supplyId": 5,
    "supplyName": "청소기"
  }
]
```

---

## Image (이미지) Resources

### 장소 이미지
**GET** `/images/places/{filename}`

장소 이미지를 제공합니다.

**경로 파라미터**
- `filename` (String, 필수): 이미지 파일명 (예: `거실.jpg`)

**예시**
```
GET /images/places/거실.jpg
GET /images/places/주방.jpg
GET /images/places/화장실.jpg
GET /images/places/침실.jpg
```

**응답** (200 OK)
- Content-Type: `image/jpeg`
- 본문: 이미지 바이너리 데이터

---

### 루틴 이미지
**GET** `/images/routines/{filename}`

루틴 단계별 이미지를 제공합니다.

**Path Parameters**
- `filename` (String, required): 이미지 파일명 (예: `1단계.jpg`)

**예시**
```
GET /images/routines/1단계.jpg
GET /images/routines/2단계.jpg
GET /images/routines/3단계.jpg
...
GET /images/routines/10단계.jpg
```

**응답** (200 OK)
- Content-Type: `image/jpeg`
- 본문: 이미지 바이너리 데이터

---

## 공통 에러 응답

### 404 Not Found
```json
{
  "error": "Place not found"
}
```

### 500 Internal Server Error
```json
{
  "error": "Internal server error",
  "message": "서버 내부 오류가 발생했습니다."
}
```

---

## 데이터 모델

### PlaceDto.PlaceSummaryRequest
| 필드명 | 타입 | 설명 |
|--------|------|------|
| placeId | Long | 장소 ID |
| placeName | String | 장소 이름 |
| placeImage | String | 장소 이미지 URL |

### PlaceDto.PlaceDetailRequest
| 필드명 | 타입 | 설명 |
|--------|------|------|
| placeId | Long | 장소 ID |
| placeName | String | 장소 이름 |
| routines | List\<RoutineDto\> | 루틴 목록 |
| supplies | List\<SupplyDto\> | 필요 물품 목록 |

### RoutineDto
| 필드명 | 타입 | 설명 |
|--------|------|------|
| routineId | Long | 루틴 ID |
| orderIndex | Long | 순서 번호 |
| title | String | 루틴 제목 |
| description | String | 루틴 설명 |
| routineImage | String | 루틴 이미지 URL |
| isComplete | Boolean | 완료 여부 |

### SupplyDto
| 필드명 | 타입 | 설명 |
|--------|------|------|
| supplyId | Long | 물품 ID |
| supplyName | String | 물품 이름 |

---

## 사용 예시

### cURL 예시

```bash
# 모든 장소 조회
curl http://localhost:8080/places

# 특정 장소 상세 조회
curl http://localhost:8080/places/1

# 특정 장소의 루틴 조회
curl http://localhost:8080/places/1/routines

# 특정 장소의 물품 조회
curl http://localhost:8080/places/1/supplies

# 이미지 다운로드
curl http://localhost:8080/images/places/거실.jpg -o 거실.jpg
```

### Swift URLSession 예시

```swift
// 모든 장소 조회
let url = URL(string: "http://localhost:8080/places")!
URLSession.shared.dataTask(with: url) { data, response, error in
    guard let data = data else { return }
    let places = try? JSONDecoder().decode([Place].self, from: data)
    print(places)
}.resume()
```

---

## 참고사항

1. **이미지 경로**: 모든 이미지 URL은 `/images/` 로 시작하는 상대 경로입니다.
2. **정렬**: 루틴은 항상 `orderIndex` 기준으로 오름차순 정렬됩니다.
3. **인코딩**: 한글 파일명은 UTF-8로 인코딩되어 있습니다.
4. **읽기 전용**: 현재 버전은 GET 요청만 지원합니다 (POST, PUT, DELETE 미지원).