package chess.domain;

import static chess.domain.Team.*;

public enum PieceType {
    PAWN_BLACK(BLACK, 1),
    ROOK_BLACK(BLACK, 5),
    KNIGHT_BLACK(BLACK, 2.5),
    BISHOP_BLACK(BLACK, 3),
    QUEEN_BLACK(BLACK, 9),
    KING_BLACK(BLACK, 0),
    PAWN_WHITE(WHITE, 1),
    ROOK_WHITE(WHITE, 5),
    KNIGHT_WHITE(WHITE, 2.5),
    BISHOP_WHITE(WHITE, 3),
    QUEEN_WHITE(WHITE, 9),
    KING_WHITE(WHITE, 0),
    NONE(NEUTRAL, 0);

    private final Team group;
    private final double score;

    PieceType(final Team group , final double score) {
        this.group = group;
        this.score = score;
    }

    public Team getTeam() {
        return group;
    }

    public double getScore() {
        return score;
    }
}
