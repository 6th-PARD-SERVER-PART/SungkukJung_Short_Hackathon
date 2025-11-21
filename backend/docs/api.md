📌 Cleaning Guide App – REST API 명세서 (Updated)

Base URL: /api


1. Place
Base URL: /api/places

   | 필드명        | 타입     | 설명                     |
   | ---------- | ------ | ---------------------- |
   | placeId    | Long   | PK                     |
   | placeName  | String | 장소명                    |
   | placeImage | String | 장소 대표 이미지 파일명          |
   | supplyId   | Long   | FK → Supply.supplyId   |
   | routineId  | Long   | FK → Routine.routineId |

1) 전체 장소 조회 — GET /places

📌 설명
모든 장소(Place)를 리스트 형태로 조회합니다.

✔ Response (200 OK)
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



2) 단일 장소 조회 — GET /places/{placeId}

📌 설명
placeId를 기반으로 특정 장소의 상세 정보를 조회합니다.

✔ Path Variable
| 이름      | 타입   | 설명        |
| ------- | ---- | --------- |
| placeId | Long | 조회할 장소 ID |

✔ Response (200 OK)
{
"placeId": 1,
"placeName": "욕실",
"placeImage": "bathroom.png",
"supplyId": 10,
"routineId": 100
}

❗ Response (404 Not Found)
{
"error": "Place not found",
"placeId": 999
}


2. Supply
   | 필드명        | 타입     | 설명                 |
   | ---------- | ------ | ------------------ |
   | supplyId   | Long   | PK                 |
   | supplyName | String | 청소 도구/세제 이름        |
   | placeId    | Long   | FK → Place.placeId |


3. Routine
   | 필드명          | 타입      | 설명                      |
   | ------------ | ------- | ----------------------- |
   | routineId    | Long    | PK                      |
   | orderIndex   | Long    | 루틴 순서를 위한 정렬 기준(sortBy) |
   | title        | String  | 루틴 단계 제목                |
   | description  | String  | 루틴 설명                   |
   | routineImage | String  | 루틴 단계 이미지               |
   | isComplete   | Boolean | 단계 완료 여부                |
   | placeId      | Long    | FK → Place.placeId      |
