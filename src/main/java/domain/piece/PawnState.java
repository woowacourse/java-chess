package domain.piece;

public enum PawnState {
    INIT_BLACK(Directions.InitBlackPawn),
    INIT_WHITE(Directions.InitWhitePawn),
    BLACK(Directions.BlackPawn),
    WHITE(Directions.WhitePawn);

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

    public void validateDirection(Direction vector) {
        directions.validateContains(vector);
    }
}
