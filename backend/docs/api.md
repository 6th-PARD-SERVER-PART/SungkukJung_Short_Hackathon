📌 Cleaning Guide App – REST API 명세서 (Updated)

Base URL: /api


1. Place
| 필드명        | 타입     | 설명                     |
| ---------- | ------ | ---------------------- |
| placeId    | Long   | PK                     |
| placeName  | String | 장소명                    |
| placeImage | String | 장소 대표 이미지 파일명          |
| supplyId   | Long   | FK → Supply.supplyId   |
| routineId  | Long   | FK → Routine.routineId |


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
