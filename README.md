# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

<hr>

## 📝 구현 기능 목록

### 🎯 체스판

- [x] 체스판의 크기는 `8 x 8`이다.
- [x] 체스판에서 말의 위치 값은 다음과 같이 구현한다.
    - 가로 위치는 왼쪽부터 오른쪽으로 `a ~ h`
    - 세로 위치는 아래부터 위로 `1 ~ 8`
- [x] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
- [x] 게임 시작 시 검은색 말은 `7 ~ 8` 행에 위치 하고, 흰색 말은 `1 ~ 2` 행에 위치 한다.
- [x] 각 기물의 초기 위치는 아래와 같다.
    ```
    RNBQKBNR  8 (rank 8)
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1 (rank 1)
    
    abcdefgh
    ```
- [x] 체스판 위에 있는 기물을 움직일 수 있다.

### ♟️ 기물

- [x] 출발지와 목적지를 받아 이동 방식에 따라 움직일 수 있는지 판별한다.
- [x] 목적지로 가는 경로에 다른 기물이 있는지 판별한다.
    - [x] 단, 나이트는 제외한다.

### 🕹️ 체스 게임

- [x] 사용자에게 커맨드를 입력 받는다.
    - [x] `start`를 입력 받으면 게임을 시작한다.
        - [x] 게임 시작 시 초기 보드를 출력한다.
    - [x] `move source위치 target위치`를 입력 받으면 기물은 이동한다.
        - [x] 출발지와 목적지를 보드에게 전달하여 이동을 요청한다.
    - [x] `end`를 입력 받으면 게임을 종료한다.

---

## 📦 프로젝트 구조

<table>
    <tr>
        <th>Package</th>
        <th>Class</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/controllers.svg?sanitize=true"/>
            <b> controller</b>
        </td>
        <td><b>ChessGame</b></td>
        <td>입력을 받아 게임 전체 진행을 담당하는 클래스</td>
    </tr>
    <tr>
        <td rowspan="2">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/home.svg?sanitize=true"/>
            <b> domain / board</b>
        </td>
        <td><b>Board</b></td>
        <td>기물들을 올려둘 수 있는 체스판</td>
    </tr>
    <tr>
        <td><b>BoardFactory</b></td>
        <td>흑백 기물들을 초기 위치에 두어 체스판을 생성하는 팩토리 클래스</td>
    </tr>
    <tr>
        <td rowspan="4">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/home.svg?sanitize=true"/>
            <b> domain / piece</b>
        </td>
        <td><b>Piece</b></td>
        <td>기물 추상 클래스</td></td>
    </tr>
    <tr>
        <td><b>Bishop, King, Knight, Pawn, Queen, Rook</b></td>
        <td>각자의 팀 색상을 가지며 이동 조건에 따라 움직임 여부를 판단하는 기물 구현체 클래스</td>
    </tr>
    <tr>
        <td><b>PieceType</b></td>
        <td>각 기물들을 상징하는 상수</td>
    </tr>
    <tr>
        <td><b>Team</b></td>
        <td>체스 게임에 존재하는 두 가지 팀 상수</td>
    </tr>
    <tr>
        <td rowspan="3">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/home.svg?sanitize=true"/>
            <b> domain / square</b>
        </td>
        <td><b>File</b></td>
        <td>체스판의 열을 의미하는 클래스</td>
    </tr>
    <tr>
        <td><b>Rank</b></td>
        <td>채스판의 행을 의미하는 클래스</td>
    </tr>
    <tr>
        <td><b>Sqaure</b></td>
        <td>체스판 한칸을 의미하는 클래스</td>
    </tr>
    <tr>
        <td rowspan="4">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/views.svg?sanitize=true"/>
            <b> view</b>
        </td>
        <td><b>Command</b></td>
        <td>사용자 입력에 따른 명령어 상수</td>
    </tr>
    <tr>
        <td><b>InputView</b></td>
        <td>전체적인 입력을 담당하는 뷰</td>
    </tr>
    <tr>
        <td><b>OutputView</b></td>
        <td>전체적인 출력을 담당하는 뷰</td>
    </tr>
    <tr>
        <td><b>PieceMapper</b></td>
        <td>기물 타입을 이름으로 변환해주는 상수</td>
    </tr>
</table>
