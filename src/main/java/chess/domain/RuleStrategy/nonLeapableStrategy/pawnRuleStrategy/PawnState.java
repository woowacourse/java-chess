package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

public class PawnState {
    private static final boolean PAWN_MOVED_STATE = true;
    private final boolean PawnMovedState;

    private PawnState() {
        this.PawnMovedState = false;
    }

    public PawnState(boolean pawnMovedState) {
        PawnMovedState = pawnMovedState;
    }

    public static PawnState initialState() {
        return new PawnState();
    }

    public static PawnState switchedPawnMovedState() {
        return new PawnState(PAWN_MOVED_STATE);
    }

    public boolean isPawnMovedState() {
        return PawnMovedState;
    }
}
