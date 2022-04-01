package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;

public class ResultView {

    private ResultView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 현황 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
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
            System.out.print(board.get(Position.of(column, row))
                    .getName());
        }
    }

    public static void printReplay(String exceptionMessage) {
        System.out.println(exceptionMessage);
        System.out.println("다시 입력 바랍니다." + System.lineSeparator());
    }

    public static void printStatus(Color color, double score) {
        System.out.println(color.name() + " : " + score);
    }

    public static void printWinner(Color color) {
        System.out.println(color.name() + "팀이 이겼습니다.");
    }
}
