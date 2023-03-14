package chess.domain.board;

public class Position {

    public final Row row;
    public final Col col;

    public Position(final String input) {
        this.row = Row.fromByInput(input.charAt(1));
        this.col = Col.from(input.charAt(0));
    }
}
