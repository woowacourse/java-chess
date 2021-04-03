# java-chess
체스 게임 구현을 위한 저장소

## 체스 게임 실행 정보

### 새로운 게임 시작 

![image](https://user-images.githubusercontent.com/63405904/113469674-7b4d6700-948a-11eb-93a8-e1bff7b954aa.png)

### 기존 게임 이어하기

### ![image](https://user-images.githubusercontent.com/63405904/113469744-1c3c2200-948b-11eb-9174-c90d40ef6c05.png) 

### Database EER 다이아그램 

<img src="https://user-images.githubusercontent.com/63405904/113469763-3c6be100-948b-11eb-97b0-485d21eaf66a.png" width="40%" /> 

### 게임 실행 데모 

[데모 링크](https://www.youtube.com/watch?v=lTuDw-Taow0)

## Todo List

## 1 단계 - 체스판 초기화 
- [x] 말 색깔 정보 Enum 객체
    - [x] Enum 값 : B, W, void
- [x] 체스 말 정보 Enum 객체
    - [x] Enum 값 : 이름
    - [x] Enum 값 : 초기 말 x, y 좌표 
- [x] 체스 말 객체 
    - [x] 체스 말 정보 Enum + 색깔 Enum 조합
- [x] 보드판 위치 객체
    - [x] x (a - h)
    - [x] y (1 - 8)
- [x] 보드판 객체 
    - [x] LinkedHashMap 자료 구조 사용
        - [x] Key : Position 객체 
        - [x] Value : 체스 말 객체
- [x] 사용자 입력 
    - [x] start : 체스판 초기화 하여 출력
    - [x] end : 게임 종료 
    - [x] 예외사항 : 
        - [x] Start/end 가 아닌 다른 입력 시 예외 처리

## 2 단계 - 말 이동
- [x] 각 말이 움직일 수 있는 규칙에 따라서 움직이도록
    - [x] 말의 종류마다 움직일 수 없는 곳으로 움직이려고 하면 예외처리
    - [x] 움직이고 싶은 방향에 대상이 있으면 뛰어 넘기 불가능
        - [x] 단, 나이트는 위의 규칙     
    - [x] 움직이려는 target 위치에 말이 있을 경우 대상 말 제거
    - [x] 이동하고 난 자리는 void/void 로 채워주기
    - [x] 보드판 영역 바깥으로 이동하지 못하도록 예외처리
    - [x] 전략 필요한 말 리스트
      - [x] King
      - [x] Queen
      - [x] Rook
      - [x] Bishop
      - [x] Knight
      - [x] Pawn        
        - [x] Pawn 만 움직이는 것과 먹는 행위 다름 주의    
        - [x] Pawn 이동 시에 뒤로 이동 불가능 및 초기 상태에 두 칸 이동 가능
- [x] move 키워드 입력시 source 위치에서 target 으로 이동
- [x] 자기 차례가 아닐 때 움직이려고 하는 경우 예외 처리 
- [x] 움직이지 않은 경우 경고 문구 -> 재입력 
- [x] 에러 처리 : 재입력 or 종료

## 3 단계 - 점수 계산
- [x] Status 키워드 입력시 점수 계산 
    - [x] Pawn 의 경우 같은 세로줄에 있다면 0.5로 계산
- [x] King이 잡혔을 경우 게임 끝내고 점수 및 승패 출력 

## 고려 사항
- [ ] PieceInfo + PieceColor 포장 객체 
- [x] Position of 다른 인자 메소드 만들기 

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
