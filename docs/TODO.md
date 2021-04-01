## 할일
* 게임 상태 관리

## 완료한 일
* Position 포장가
* 64 리스트인 체스보드를 매핑해 pieces 출력
* 체스 보드에서 체스 말에게 위치값 넣어주면서 초기화
* position 변수를 String으로 다 바꾸기, 연산도 이 객체에서하기
* currentPieces에서 기물 삭제 기능 구현해야함 
* enum에서 함수형 인터페이스로 방향 구하는 거 하고있었음
* move()했는데, 잡아먹는 기능 해야함

---
#### 궁금한 것

#### ChessBoard 출력
저희는 piece 리스트로 현재 기물들을 관리하고 있습니다.  
현재 체스 보드를 출력할 때, 이미 캐싱되어 있는 position 위치에 따라 
기물을 출력하기 위해 다음과 같은 로직을 가지고 있는데요,  
OutputView는 출력만을 위한 객체인데 해당 position에 대응하는 piece를 찾고 있어  
OutputView가 지지 말아야 할 책임을 지고 있는게 아닌가 하는 의문이 들었습니다 ! 
출력을 위해 Position별로 기물들이 대응된 ChessBoard 객체를 만들까 고민도 했지만, 
출력만을 위한 객체가 될 것 같아서 일단 이러한 방식으로 구현했습니다 🥲    
ChessBoard라는 객체를 만들어 이 객체를 출력하는 것이 더 좋은 방법일까요 ?

``` java
public static void printChessBoard(Pieces pieces) {
    int index = 0;
    for (String xy : CACHE.keySet()) {
        checkChangeLinePoint(index);
        Piece piece = pieces.findByPosition(CACHE.get(xy));
        System.out.print(piece.getName());
        index++;
    }
    System.out.println();
}
```

#### Diagonal, Cross
대각선과 십자 방향에 관한 로직을 이 두 Enum에서 담당하고 있습니다.  
모든 기물들은 각각의 이동 규칙에 따라 십자 또는 대각선의 경로로 이동하는데,  
위, 아래, 양 옆, 각각의 대각선 방향을 찾고 이 경로에 대한 검증이 필요한데,  
각각의 기물에다 이 로직을 구현하면 중복이 발생하는 것 같아 객체를 만들어 
방향에 대한 검사를 하도록 구현했습니다 !  
덕분에 각 기물들의 이동 기능 코드 수는 팍 줄일 수 있었지만,  
이렇게 경로에 대한 검사를 다른 객체가 하는게 맞나 페어도 함께 의문을 가졌어요 !    
책임을 억지로 분리한 것이 아닌지 확신이 들지 않습니다 🥲

#### Command
사용자가 입력한 명령어에 따라 어떤 행위를 실행해야하는데,  
명령어중 첫째 명령어를 Enum으로 관리하고,  
게임 상태에 대한 전략(?)을 또 다시 Enum으로 관리하고 있습니다.  
그런데 도메인으로 둔 FirstCommand에서 컨트롤러를 호출하는 것이 옳은가 의문이 들어요 !  
(command 패턴에 대해서도 봤지만 시간이 부족해 적용하진 못했어요) 
아니면 컨트롤러쪽으로 보면 되는 것인지 요런 저런 고민들을 하며 확신이 서지 않는 부분이에요.  

