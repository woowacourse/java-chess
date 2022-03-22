package chess.domain.piece;

public enum Name {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    NONE(".");

    private final String name;

    Name(String name) {
        this.name = name;
    }

    public String getName(Team team) {
        if (team.isBlack()) {
            return name.toUpperCase();
        }
        return name.toLowerCase();
    }
}
