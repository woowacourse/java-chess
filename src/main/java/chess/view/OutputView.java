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
    /*

    public static void printRule() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printScore(double score) {
        System.out.println("점수 :" + score);
    }

    public static void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

     */
}
