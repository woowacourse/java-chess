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
