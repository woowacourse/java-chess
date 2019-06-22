package chess.domain.RuleImpl;

public class Empty extends AbstractRule {
    private static Empty INSTANCE = new Empty();
    private static final double SCORE = 0;

    private Empty() {
        super(SCORE);
    }

    public static Empty getInstance() {
        return INSTANCE;
    }
}

