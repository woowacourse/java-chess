package domain.piece;

import domain.Rank;
import domain.Square;

public enum PawnState {
    INIT_BLACK(Directions.InitBlackPawn),
    INIT_WHITE(Directions.InitWhitePawn),
    BLACK(Directions.BlackPawn),
    WHITE(Directions.WhitePawn);

    public static final Rank INIT_BLACK_RANK = Rank.SEVEN;
    public static final Rank INIT_WHITE_RANK = Rank.TWO;
    private final Directions directions;

    PawnState(Directions directions) {
        this.directions = directions;
    }


    public static PawnState init(TeamColor teamColor) {
        if (teamColor.isBlack()) {
            return INIT_BLACK;
        }
        return INIT_WHITE;
    }

    public PawnState next() {
        if (this == INIT_BLACK) {
            return BLACK;
        }
        if (this == INIT_WHITE) {
            return WHITE;
        }
        return this;
    }

    public boolean isMoved(Square square) {
        if (this == BLACK || this == WHITE) {
            return true;
        }
        if (this == INIT_BLACK) {
            return square.isDifferent(INIT_BLACK_RANK);
        }
        return square.isDifferent(INIT_WHITE_RANK);
    }

    public void validateDirection(Direction direction) {
        directions.validateContains(direction);
    }
}
