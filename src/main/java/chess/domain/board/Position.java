package chess.domain.board;

public class Position {

    private final Row row;
    private final Col col;

    private Position(final Row row, final Col col) {
        this.row = row;
        this.col = col;
    }

    public static Position from(final String input) {
        Row row = Row.fromByInput(input.charAt(1));
        Col col = Col.fromByInput(input.charAt(0));
        return new Position(row, col);
    }
}
