package chess.domain.piece;

import chess.domain.Team;

public enum PieceType {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    EMPTY(".");

    private final String pieceLetter;

    PieceType(String visualizedPiece) {
        this.pieceLetter = visualizedPiece;
    }

    public String getPieceLetter(Team team) {
        if (team == Team.BLACK) {
            return pieceLetter.toUpperCase();
        }
        return pieceLetter;
    }
}
