package chess.domain.rule;

public class Empty extends AbstractRule {
    private static Empty INSTANCE = new Empty();

    private Empty() {
        super(Type.EMPTY);
    }

    public static Empty getInstance() {
        return INSTANCE;
    }
}

