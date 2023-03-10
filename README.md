# Baedal Web Service
회원 가입, 로그인, 배달 조회, 배달 주문 수정 서비스를 위한 Back-End API

## 회원 가입 API
1.	회원가입시 필요한 정보는 ID, 비밀번호, 사용자 이름 입니다.
2.	비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로
      12자리 이상의 문자열로 생성해야 합니다. 
3. 비밀번호에 공백이 포함 될 수 없습니다.

## 로그인 API
1.	사용자로부터 ID, 비밀번호를 입력받아 로그인을 처리합니다.
2.	ID와 비밀번호가 이미 가입되어 있는 회원의 정보와 일치하면 로그인이 되었다는 응답으로 AccessToken 을 제공합니다.

## 배달 조회 API
1.	배달 조회 시 필요한 정보는 기간 입니다. (기간은 최대 3일)
2.	기간 내에 사용자가 주문한 배달의 리스트를 제공합니다.

## 배달 주문 수정 API (도착지 주소 변경)
1.	사용자로부터 도착지 주소를 요청 받아 처리합니다.
2.	사용자가 변경 가능한 배달인 경우에만 수정이 가능합니다.

## 기타
1.	DataBase 를 반드시 사용해주세요.
      (필수 데이터 외에 추가가 기본적으로 필요하다고 생각되는 데이터들도 같이 설계 해주세요.)
2.	Spring MVC 기반으로 전체 Application 설계를 해주세요.
3.	최대한 많은 예외케이스를 표현해 주세요.
4.	AccessToken 은 특별한 사유가 없다면 JWT 를 적용해주세요.
5.	Test Code 는 작성이 가능하다면 작성해주세요.
6.	API 명세서 작성이 가능하다면 같이 제공해주세요.

## API Document
- 프로젝트 실행 후 `/docs/api.html` 로 접근합니다.

## ER Diragram
```mermaid
erDiagram
    Member ||--o{ Delivery : places
    Member {
	    bigint id
	    string email
        string name
        string password
        timestamp created_at
        timestamp modified_at
    }
    Delivery {
        bigint id
        bigint member_id
		string destination
		string status
        timestamp created_at
        timestamp modified_at
    }
```

## TODO
- Store(배달 대행 플랫폼 고객사) 추가
  - Rider에 배달 대행을 요청하는 주체
  - Store는 배달 추가 API를 호출한다
  - Store는 주문을 취소할 수 있다.
- Rider(라이더) 추가 
  - Rider가 배달 완료시, 주문상태가 '주문완료'로 변경된다
- Rider 배차 기능 추가
  - Rider에 배달을 요청하고 수락시 해당 Rider에 배차된다
  - Rider에 배차가 되면, 주문상태가 '배달 중'으로 변경된다.