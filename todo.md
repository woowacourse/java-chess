# todo
- 방별로 게임을 조회하고, 게임을 진행할 수 있다.
    - dao의 select문, update문을 수정한다.
    
- dao 리팩토링
    - chessboard -> string 변환 함수를 다른 클래스로 보낸다.
    - string -> chessboard 변환 함수를 다른 클래스로 보낸다.

- chessGame 클래스의 몇 기능을 다른 클래스로 보낸다.
    - 점수 계산하는 기능을 다른 클래스로 보낸다.


- 현재 상황: pawn의 validateDirection(Vector vector) 하는 일이 없다.

- 현재 상황: connection.close -> dao를 쓸 수 없다.
          connection, dao는 서버에서 한 번만 실행된다.
          
  다른 상황: connection.close -> dao에서 connection을 새로 만든다.
  

# done
- 체스판 출력
