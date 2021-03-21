## 할일
컨트롤러 play에서 command도 넘겨줘야하지 않을까... 

* chessBoard 만들기
* command에 enum 쓰기
첫 단계에서는 firstCommand를 받는다.
* position 이름 생각하기
* piece한테 메시지 보내서 position 비교하기

## 완료한 일
* Position 포장가
* 64 리스트인 체스보드를 매핑해 currentPieces 출력
* 체스 보드에서 체스 말에게 위치값 넣어주면서 초기화
* position 변수를 String으로 다 바꾸기, 연산도 이 객체에서하기
* currentPieces에서 기물 삭제 기능 구현해야함 
* enum에서 함수형 인터페이스로 방향 구하는 거 하고있었음
* move()했는데, 잡아먹는 기능 해야함

---
#### 궁금한 것
output view에서 많은 일을 하고 있지 않은가.
``` java
public static void printChessBoard(CurrentPieces currentPieces) {
    for (int i = 0; i < POSITIONS.size(); i++) {
        checkChangeLinePoint(i);
        Piece piece = currentPieces.findByPosition(POSITIONS.get(i));
        System.out.print(piece.getName());
    }
    System.out.println();
}
```
이정도 로직은 괜찮나여..?
아니면 chessBoard 객체를 만들까요

position of랑 생성자랑 검증 유무...