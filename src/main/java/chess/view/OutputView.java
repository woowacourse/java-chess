package chess.view;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void gameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료: end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        for (List<Piece> rank : chessBoard.getChessBoard()) {
            for (Piece piece : rank) {
                System.out.print(piece.getName());
            }
            System.out.println();
        }
        System.out.println();
    }
}
