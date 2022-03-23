package chess.view;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Piece;
import java.util.Map;

public class ResultView {

    private ResultView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(Map<Position, Piece> board) {
        for (Row row : Row.getRowsReverseOrder()) {
            printBoardInRow(board, row);
            System.out.println();
        }
        System.out.println();
    }

    private static void printBoardInRow(Map<Position, Piece> board, Row row) {
        for (Column column : Column.values()) {
            System.out.print(board.get(new Position(column, row))
                    .getName());
        }
    }

    public static void printReplay(String exceptionMessage) {
        System.out.println(exceptionMessage);
        System.out.println("다시 입력 바랍니다.");
    }
}
