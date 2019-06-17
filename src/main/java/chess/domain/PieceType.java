package chess.domain;

import static chess.domain.Team.*;

public enum PieceType {
    PAWN_BLACK(BLACK),
    ROOK_BLACK(BLACK),
    KNIGHT_BLACK(BLACK),
    BISHOP_BLACK(BLACK),
    QUEEN_BLACK(BLACK),
    KING_BLACK(BLACK),
    PAWN_WHITE(WHITE),
    ROOK_WHITE(WHITE),
    KNIGHT_WHITE(WHITE),
    BISHOP_WHITE(WHITE),
    QUEEN_WHITE(WHITE),
    KING_WHITE(WHITE),
    NONE(NEUTRAL);

    private final Team group;

    PieceType(final Team group) {
        this.group = group;
    }

    public Team getTeam() {
        return group;
    }
}
