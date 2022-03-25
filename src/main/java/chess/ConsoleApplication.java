package chess;

import chess.controller.ChessController;

public class ConsoleApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }
}

// TODO: 게임 종료 조건, 최종 점수 계산 (status)
// TODO BONUS :  폰 테스트케이스 추가
/*
<-------Rank---------->
 BLACK
 R  N  B  Q  K  B  N  R
A8 B8 C8 D8 E8 F8 G8 H8
A7 B7 C7 D7 E7 F7 G7 H7  ⬆️
A6 B6 C6 D6 E6 F6 G6 H6  F
A5 B5 C5 D5 E5 F5 G5 H5  I
A4 B4 C4 D4 E4 F4 G4 H4  L
A3 B3 C3 D3 E3 F3 G3 H3  E
A2 B2 C2 D2 E2 F2 G2 H2  ⬇️
A1 B1 C1 D1 E1 F1 G1 H1
 R  N  B  Q  K  B  N  R
 WHITE

Rank : a, b, c
File : 1, 2, 3
 */