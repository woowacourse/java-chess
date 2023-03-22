package chess.view;

import chess.domain.Team;

public enum PieceView {

    KING("K", "k"),
    QUEEN("Q", "q"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    KNIGHT("N", "n"),
    PAWN("P", "p"),
    EMPTY(".", ".");

    private final String black;
    private final String white;

    PieceView(String black, String white) {
        this.black = black;
        this.white = white;
    }

    public String getPieceView(Team team) {
        if (team == Team.BLACK) {
            return this.black;
        }
        return this.white;
    }
}
