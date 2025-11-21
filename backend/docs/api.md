# API 명세서

## 기본 정보
- Base URL: `https://api.example.com/v1`
- Content-Type: `application/json`

---

## House API

### 1. house
POST `/house`

| Key          | 타입     | 설명   |
|--------------|--------|------|
| `houseId`    | Long   | PK   |
| `houseName`  | String | 집 이름 |
| `inviteCode` | String | 초대코드 |


### 2. member

## Member
| 필드명       | 타입   | 설명                    |
|-----------|--------|-----------------------|
| memberId  | Long   | Member 고유 Primary Key |
| name      | String | 사용자 이름                |
| status    | String | 사용자 상태                |
| imageName | String | 사용자 이미지               |
| houseId   | Long   | House FK (어느 집에 속했는지) |



### 3. commnet
| 필드명         | 타입    | 설명                     |
|-------------|---------|------------------------|
| commentId   | Long    | Comment 고유 Primary Key |
| content     | String  | comment의 내용            |
| isCompleted | Boolean  | 완료 여부                  |
| isChecked   | Boolean | 완료 여부 확인 여부            |
| memberId    | Long    | Member FK (게시글 작성자)    |