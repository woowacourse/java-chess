# 학습로그 2-1

# [DB] MySQL query - 5

## 내용
- primaryKey는 유일키이다. (중복 불가)
- CLI 환경에서 MySQL 확인하는 명령어들
```text
// 도커에서 띄우기 (설치 과정은 생략)
docker exec -it <이름> bash
mysql -u root -p
<비밀번호>
// --> 접속완료
```
```text
show databases;
use <특정 데이터 베이스>;

CREATE TABLE <테이블이름> (
	id int NOT NULL AUTO_INCREMENT,
	game_id int NOT NULL,
	... 해당하는 내용들 ...
	PRIMARY KEY (id)
);

INSERT INTO <테이블이름> (키들을 특정(없을시 전체)) VALUES (해당 키에 해당하는 값);

SELECT * FROM chessTable;
```

# [웹 UI] 렌더링 - 4

## 내용
- SSR: 서버에서 온전한 html을 보내줘서 화면 전체를 업데이트 
    - 장점: SEO(검색엔진최적화)에 유리, 빠른 초기 로딩속도
    - 단점: 사용자 친화적이지 않음, 깜빡임현상, 서버부하가 많이 됨
- CSR: 서버에서는 리소스만 받고(API를 통해) 클라이언트 측에서 해당 내용을 조작하여 화면을 업데이트 
    - 장점: 빠른 속도 및 서버부하 감소
    - 단점: 초기 로딩속도가 느림
    
- 컨텐츠 중심의 개발이 필요하다
    - 단순한 정적 컨텐츠 -> 서버 사이드 렌더링
    - 동적인 컨텐츠 -> 클라이언트 사이드 렌더링 
## 참고
- 테코톡

# [DB] 객체관리 - 4
## 내용
- 객체와 String 타입의 key=value 값을 상호변환해주기 위해서는 dao, dto 등의 클래스들이 사용된다.
- dao: Data Access Object로 DB의 값에 직접 접근하는 객체
- dto: Data Transfer Object로 View단과 객체를 주고받을 때 사용하는 객체
- dao, dto의 필요성을 전보다 좀 더 강하게 느낄 수 있었다.
- DB와 Domain을 연결하기 위해서는 사이에 값들을 객체를 통해 문자화 해주는 작업이 필요했다.

# [JS] 이벤트 - 5
## 내용
- event.target: 내가 선택한 바로 그 Node!
- event.currentTarget: 내가 이벤트를 등록해준 그 Node! (더 넓은 범위)

# 학습로그 2-2

# [OOP] Package구조 - 3
## 내용
- 일반적으로 domain, service, dao 패키지로 나뉘는 듯 하다.
- dao: DB에 접속하여 데이터를 가져오는 클래스. 일반적으로 Dao는 테이블당 하나 만들게 됨
- service: domain과 dao로 직접 접근하여 기능 구현. dao 연동뿐 아니라 데이터베이스에 종속되지 않는 로직을 구현.
- dao, service 모두 호환을 위해 인터페이스를 만들고 구현하는 방식을 사용하기도 함

## 링크
[패키지 구조를 어떻게 가져가는게 좋을까?](https://www.slipp.net/questions/36)
[계층별 패키지 구성하기](https://12bme.tistory.com/271)
- 스프링 입문을 위한 자바 객체 지향의 원리와 이해(첵)

# [Structure] Service Layer - 4
## 내용
- Data Access Layer: 개별 SQL을 처리하는 것을 목표로 한다.
- Service Layer: 여러 Data Access Layer를 하나로 묶어 관리한다. 데이터 베이스에 대한 트랜젝션을 관리한다.
- 기능 단위로 interface의 책임을 작게 구성하는 것이 좋다.
- 의존성주입을 통해 Service를 구현한 class가 사용되는 듯 하다.

## 링크
[Service Layer](https://goodteacher.tistory.com/252)
[Service의 적절한 크기 가이드](https://www.popit.kr/spring-guide-service-%EC%A0%81%EC%A0%88%ED%95%9C-%ED%81%AC%EA%B8%B0-%EA%B0%80%EC%9D%B4%EB%93%9C/)

# [Structure] VO, DTO - 3
## 내용
- DTO(Data Transfer Object)
  - 데이터 전달용, 데이터를 담아 전달하는 바구니
  - 계층 간 데이터를 전달 (Controller~Service)
  - getter, setter 이외의 다른 로직을 갖지 않음 
  - DTO도 불변객체 가능
- VO(Value Object)
  - 값 표현용, 값 그 자체를 표현하는 객체
  - equals, hashCode 오버라이딩을 하여 비교한다.
  
## 참고
- 테코톡
