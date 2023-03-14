package chess.domain.board;

public class Position {

    private final Row row;
    private final Col col;

    public Position(final String input) {
        this.row = Row.from(String.valueOf(input.charAt(1)));
        this.col = Col.from(String.valueOf(input.charAt(0)));
    }
}
