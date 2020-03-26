package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Row;
import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class OutputView {
    public static void printBoard(ChessBoard chessBoard) {
        List<Row> board = chessBoard.getBoard();
        for (int i = 8; i >= 1; i--) {
            for (int j = 0; j <= 7; j++) {
                Row row = board.get(i - 1);
                ChessPiece chessPiece = row.get(j);
                System.out.print(chessPiece.getName());
            }
            System.out.println();
        }
    }

    public static void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }


    public static void printWrongCommandMessage() {
        System.out.println("잘못된 커맨드입니다.");
    }
}
