package chess.domain;

public class Player {

    private final String color;
    private final Pieces pieces;

    private Player(final String color, final Pieces pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public static Player from(final String color, final Pieces pieces) {
        return new Player(color, pieces);
    }

}
