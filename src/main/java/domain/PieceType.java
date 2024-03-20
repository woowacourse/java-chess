package domain;

import static domain.TeamColor.BLACK;
import static domain.TeamColor.WHITE;

public enum PieceType {
    BLACK_PAWN(BLACK),
    BLACK_ROOK(BLACK),
    BLACK_KNIGHT(BLACK),
    BLACK_BISHOP(BLACK),
    BLACK_QUEEN(BLACK),
    BLACK_KING(BLACK),
    WHITE_PAWN(WHITE),
    WHITE_ROOK(WHITE),
    WHITE_KNIGHT(WHITE),
    WHITE_BISHOP(WHITE),
    WHITE_QUEEN(WHITE),
    WHITE_KING(WHITE);

    private final TeamColor teamColor;

    PieceType(final TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public boolean hasColor(TeamColor teamColor) {
        return this.teamColor.equals(teamColor);
    }
}
