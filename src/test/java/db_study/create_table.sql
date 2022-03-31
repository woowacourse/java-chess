create table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id) -- id 컬럼을 primary key로 설정
);
-- varchar : 변할 수 있는 문자들. 한영 무관한 글자수.
-- int, bigint, date, timestamp 등등 DB만의 타입 체계 존재

create table role -- 외래키가 '걸려있는' 테이블
(
    user_id varchar(10) not null,
    role    varchar(10) not null,
    primary key (user_id),                       -- 해당 테이블 내에서 고유한 값. 식별 목적.
    foreign key (user_id) references member (id) -- 다른 테이블로부터 온 데이터.
);
-- 도커 재실행하지 않아도 mysql 서버에 등록하여 직접 sql 실행하여 추가 가능

insert into role (user_id, role)
values ('not_exist', 'admin'); -- 저장 실패
insert into role (user_id, role)
values ('jeong_id', 'admin'); -- 저장 성공 : 이미 jeong_id 라는 id 값이 member 테이블에 존재하므로

select *
from member
         join role on member.id = role.user_id;
-- m * n개로 cross join되는 것이 아니라 대응되는 것만 오도록 inner join

