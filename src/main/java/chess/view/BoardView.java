package chess.view;

import chess.board.Board;
import chess.board.Color;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import java.util.Map;

public final class BoardView {

    private final Map<Row, Character> rowMap = Map.of(
            Row.ONE, '1',
            Row.TWO, '2',
            Row.THREE, '3',
            Row.FOUR, '4',
            Row.FIVE, '5',
            Row.SIX, '6',
            Row.SEVEN, '7',
            Row.EIGHT, '8');

    public void display(Board board) {
        System.out.println();
        for (Row row : Row.values()) {
            System.out.printf("%c ", rowMap.get(row));
            for (Column column : Column.values()) {
                Position position = new Position(row, column);
                Color color = board.colorAt(position);
                if (color == Color.EMPTY) {
                    System.out.print(".");
                } else {
                    System.out.printf("%c", PieceNotation.of(color, board.pieceTypeAt(position)));
                }
            }
            System.out.println();
        }
        System.out.println("  ABCDEFGH");
        System.out.print("> ");
    }
}
