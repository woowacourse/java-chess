package chess.view;

import java.util.Map;

import chess.Board;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;

public class ResultView {

    Map<Integer, Column> columns = Map.of(
            1, Column.A,
            2, Column.B,
            3, Column.C,
            4, Column.D,
            5, Column.E,
            6, Column.F,
            7, Column.G,
            8, Column.H
    );

    Map<Integer, Row> rows = Map.of(
            8, Row.EIGHT,
            7, Row.SEVEN,
            6, Row.SIX,
            5, Row.FIVE,
            4, Row.FOUR,
            3, Row.THREE,
            2, Row.TWO,
            1, Row.ONE
    );

    public void printBoard(final Board board) {
        Map<Position, Piece> getBoard = board.getBoard();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Position position = new Position(rows.get(Math.abs(i - 9)), columns.get(j));
                if (getBoard.containsKey(position)) {
                    sb.append(getBoard.get(position).getDisplay());
                } else {
                    sb.append(".");
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

}
