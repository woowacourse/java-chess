package chess.domain.board;

import chess.domain.position.Row;
import chess.domain.position.Position;
import chess.domain.position.Column;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, String> board = new HashMap<>();

    public void initBoard() {
        initWhitePieces();
        initBlackPieces();
    }

    private void initBlackPieces() {
        board.put(new Position(Column.A, Row.EIGHT), "R");
        board.put(new Position(Column.B, Row.EIGHT), "N");
        board.put(new Position(Column.C, Row.EIGHT), "B");
        board.put(new Position(Column.D, Row.EIGHT), "G");
        board.put(new Position(Column.E, Row.EIGHT), "K");
        board.put(new Position(Column.F, Row.EIGHT), "B");
        board.put(new Position(Column.G, Row.EIGHT), "N");
        board.put(new Position(Column.H, Row.EIGHT), "R");

        initOneLine(Row.SEVEN, "P");
    }

    private void initWhitePieces() {
        board.put(new Position(Column.A, Row.ONE), "r");
        board.put(new Position(Column.B, Row.ONE), "n");
        board.put(new Position(Column.C, Row.ONE), "b");
        board.put(new Position(Column.D, Row.ONE), "g");
        board.put(new Position(Column.E, Row.ONE), "k");
        board.put(new Position(Column.F, Row.ONE), "b");
        board.put(new Position(Column.G, Row.ONE), "n");
        board.put(new Position(Column.H, Row.ONE), "r");

        initOneLine(Row.TWO, "p");
    }

    private void initOneLine(Row row, String string) {
        for (Column column : Column.values()) {
            board.put(new Position(column, row), string);
        }
    }

    public Map<Position, String> getBoard() {
        return board;
    }

    public void move(Position beforePosition, Position afterPosition) {
        String piece = board.get(beforePosition);
        // TODO - if piece.movable
        board.put(afterPosition, piece);
        board.remove(beforePosition);
    }

    public boolean isEmpty() {
        return board.isEmpty();
    }
}
