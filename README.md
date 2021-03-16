# java-chess
체스 게임 구현을 위한 저장소

## 1 단계 - 체스판 초기화 
- [x] 말 색깔 정보 Enum 객체
    - [x] Enum 값 : B, W, void
    - [ ] Enum 값 : 초기 말 y 좌표
- [x] 체스 말 정보 Enum 객체
    - [x] Enum 값 : 이름
    - [ ] Enum 값 : 초기 말 x 좌표 
- [x] 체스 말 객체 
    - [x] 체스 말 정보 Enum + 색깔 Enum 조합
- [x] 보드판 위치 객체
    - [x] x (a - h)
    - [x] y (1 - 8)
- [ ] 보드판 객체 
    - [ ] LinkedHashMap 자료 구조 사용
        - [ ] Key : Position 객체 
        - [ ] Value : 체스 말 객체
- [ ] 사용자 입력 
    - [ ] start : 체스판 초기화 하여 출력
    - [ ] end : 게임 종료 
    - [ ] 예외사항 : 
        - [ ] Start/end 가 아닌 다른 입력 시 예외 처리
    

## 고려 사항
- [ ] PieceInfo + PieceColor 포장 객체 

## 구성요소
### 말
#### 킹
#### 퀸
#### 나이트
#### 비숍
#### 룩
#### 폰
#### 보이드


### 보드판
#### 보드판
#### 위치


### 게임
