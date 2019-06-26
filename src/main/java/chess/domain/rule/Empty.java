package chess.domain.rule;

import chess.domain.Rule;

public class Empty extends Rule {
    private static Empty INSTANCE = new Empty();

    private Empty() {
        super(Type.EMPTY);
    }

    public static Empty getInstance() {
        return INSTANCE;
    }
}

