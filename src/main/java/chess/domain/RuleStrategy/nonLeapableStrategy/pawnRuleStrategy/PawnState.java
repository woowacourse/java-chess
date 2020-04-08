package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

public class PawnState {
    private final boolean PawnInitialState;

    private PawnState(boolean pawnState) {
        this.PawnInitialState = pawnState;
    }

    public static PawnState initialState() {
        return new PawnState(true);
    }

    public static PawnState MovedState() {
        return new PawnState(false);
    }

    public PawnState switchedMovedState(){
        return MovedState();
    }

    public boolean isInitialState() {
        return PawnInitialState;
    }
}
