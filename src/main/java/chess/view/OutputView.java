package chess.view;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameStart() {
        System.out.println("체스 게임을 시작합니다.\n"
                + "게임 시작 : start\n"
                + "게임 종료 : end\n"
                + "게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Map<Coordinate, Piece> value) {
        for (Row row : Row.values()) {
            printBoardInSameRow(value, row);
            System.out.println();
        }
    }

    private static void printBoardInSameRow(Map<Coordinate, Piece> value, Row row) {
        for (Column column : Column.values()) {
            System.out.print(value.get(Coordinate.of(column, row)).getSymbolByTeam());
        }
    }

    public static void printStatus(Map<String, Double> status) {
        status.forEach((team, count) -> System.out.println(team + " : " + count + "점"));
    }
}
