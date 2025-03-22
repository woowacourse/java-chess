package chess.piece;

public enum PieceType {
    BISHOP("B"),
    KING("K"),
    KNIGHT("N"),
    PAWN("P"),
    QUEEN("Q"),
    ROOK("R"),
    ;

    private final String title;

    PieceType(String title) {
        this.title = title;
    }

    public String getTitleByTeam(Team team) {
        return switch (team) {
            case BLACK -> title.toLowerCase();
            case WHITE -> title.toUpperCase();
        };
    }
}
