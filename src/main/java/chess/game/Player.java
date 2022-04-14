package chess.game;

public enum Player {

    BLACK("Black"),
    WHITE("White"),
    NONE("None"),
    ;

    private final String name;

    Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getOpponentName() {
        if (this == WHITE) {
            return BLACK.name;
        }
        if (this == BLACK) {
            return WHITE.name;
        }
        return NONE.name;
    }
}
