# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

## 용어 정리

- rank: 체스판에서의 가로줄(row)을 의미
- file: 체스판에서의 세로줄(column)을 의미
- square: 체스판의 한 칸을 의미
- turn: 해당 시점에서 게임을 진행중인 팀을 의미

## 기능 요구 사항

### 입력

-[x] 사용자로부터 command 입력을 받는다
    - [x] start를 입력하면 게임을 시작한다
    - [x] end를 입력하면 게임이 종료된다
    - [x] move를 입력하면 말이 이동한다
        - [x] 입력은 move source위치 target위치 의 형태로 입력한다
    - [ ] status를 입력하면 현재 상태의 결과를 볼 수 있다
- [x] 올바르지 않은 명령을 입력하면 다시 입력받는다.

### 게임 진행

- [x] 체스판을 초기화한다
    - [x] 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이다
    - [x] 말의 위치 값은 세로는 아래부터 위로 1 ~ 8이다
    - [x] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다

### 게임 규칙

- [x] White팀, Black팀이 번갈아가면서 게임을 진행한다
    - [x] 게임을 시작하면 White팀이 먼저 턴을 시작한다
- [x] 나이트를 제외한 다른 말들은 경로 상에 다른 말이 있다면 뛰어넘을 수 없다
- [x] King
    - [x] 상, 하, 좌, 우, 대각선으로 한 칸만 움직일 수 있다
- [x] Queen
    - [x] 상, 하, 좌, 우, 대각선으로 움직일 수 있다
- [x] Rook
    - [x] 상, 하, 좌, 우로 움직일 수 있다
- [x] Knight
    - [x] 상, 하 1칸 + 좌, 우 2칸 / 상, 하 2칸 + 좌, 우 1칸으로 움직일 수 있다
    - [x] 이동 경로에 다른 말이 있어도 뛰어넘어 움직일 수 있다
- [x] Bishop
    - [x] 대각선으로 움직일 수 있다
- [x] Pawn
    - [x] 첫 이동은 상대 방향으로 1칸, 혹은 2칸 움직일 수 있다
    - [x] 첫 이동이 아닌 경우 상대 방향으로 1칸만 움직일 수 있다
    - [x] 대각선에 상대방 말이 있는 경우 말을 잡고, 그 위치로 이동할 수 있다

### 게임 승패 판단

- [x] King이 잡히면 게임에서 진다
    - [x] 두 팀중 한팀이라도 King이 잡히면 게임이 종료된다
- [x] 현재까지 남아있는 말에 대한 점수를 계산한다
    - [x] Queen(9점), Rook(5점), Bishop(3점), Knight(2.5점), King(0점)이다
    - [x] Pawn 기본점수는 1점이며 세로줄에 같은 색의 폰이 있는 경우에는 0.5점이다
    - [x] 점수가 큰 팀이 승리한다

### 출력

- [x] 체스판을 출력한다
- [x] King이 잡히면 각 팀의 승패 결과를 출력한다
- [ ] 각 팀의 점수와 승패 결과를 출력한다

---

## 고민한 포인트

### Position 캐싱 적용

체스판에는 64개의 고정된 위치가 있습니다. 체스 말을 이동할 때 명령어에 해당하는 Position을 찾아야 합니다.<br>
이때마다 Position을 새로 생성하는것은 효율적이지 못하다고 생각했습니다. 그래서 캐싱을 적용하고 file, rank에 해당되는 Position을 리턴하도록 했습니다.

### Piece(체스 말), GameState(현재 게임 진행 상태) 구현 방식

인터페이스에 체스 말에 따라 달라질 동작들을 지정해두고 이를 구현하는 각 클래스에서 말의 종류에 따른 동작을 정의하도록 하였습니다.<br>
이 때 Piece 구현 클래스의 경우 어느 팀의 말인지 알기 위해 Color를 필드로 갖습니다.
Piece를 구현한 모든 클래스에 Color 필드가 있고 필드를 사용하는 공통된 메서드가 중복으로 존재합니다.<br>
만약 인터페이스 구현 방식이 아니라 상속을 사용하면 중복 필드와 중복 메서드를 줄일 수 있습니다.
이렇게 중복 코드와 중복 메서드가 있는 경우 혹시 어떤 방식을 사용하시는지 궁금합니다.<br>
-> 제가 생각한 결론: 상속의 경우 기능 확장, 수정에 제한이 생길 수 있습니다.
만약 각 Piece구현 클래스들에 새로운 기능목록이 추가되어야한다면 상속을 통해 구현한 경우 다중 상속을 할 수 없습니다. 또한 상위 클래스의 변경에 영향을 받습니다. <br>
인터페이스로 구현한 경우 여러 인터페이스를 구현하는것이 가능합니다.
중복 코드가 발생한다는 점은 있지만 확장, 수정 가능성을 고려하면 인터페이스 활용이 더 유연하다고 생각해서 인터페이스를 적용했습니다.

### 테스트 코드 필드 초기화

페어와 테스트코드를 작성하다가 필드 초기화 관련 궁금증이 생겼습니다.
Piece(Pawn, Knight, King 등)의 테스트는 해당 말이 주어진 위치에서 다른 위치로 이동할 수 있는지 확인하고 있습니다.<br>
이 때 각 테스트에서 Piece는 동일하게 사용되므로 테스트코드 밖에서 초기화 해주었습니다.
이 때 어짜피 변경되지 않는 값이라면 ```@BeforeEach```를 사용하지 않고 처음 한번만 ```Bishop bishop = new Bishop(Color.WHITE);``` 로
해주어도 되지 않는가 하는 이야기가 나왔습니다.<br>
혹시 이렇게 변경되지 않는 값이라면 굳이 @BeforeEach를 사용하지 않고 한번만 초기화해주어도 될까요?<br>
-> 제가 생각한 결론: 지금 현재는 생성된 piece에 영향을 주는 테스트 케이스가 없지만 향후 다른 케이스가 추가될 수도 있고,
테스트를 할 때에는 초기 상태가 항상 정확하게 관리되어야 할 것 같습니다.<br>
그래서 각 테스트케이스가 수행되기 전에 @BeforeEach로 초기화 되도록 하였습니다.

### 리플렉션을 활용한 테스트

이번 미션의 요구사항 중 getter를 사용하지 않는다는 조건이 있었습니다. 그래서 테스트를 위한 getter도 최대한 사용하지 않고 구현하려고 노력했습니다.<br>
하지만 테스트를 하며 필드에 접근해야하는 경우가 있었고 리플렉션을 통해 접근할 수 있다는 것을 알게 되었습니다.<br>
이 방법을 통해 getter 없이 테스트는 할 수 있었으나 이 방식이 나은지, 혹은 정말 필요하다면 getter를 열어두는것이 나은지 고민되었습니다.
혹시 현업에서도 이 방식을 사용하시는지 궁금합니다!<br>
-> 리플렉션에 대해서는 아직 이해가 부족해서 추가로 학습해야할 것 같습니다.
