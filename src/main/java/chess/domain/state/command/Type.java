package chess.domain.state.command;

import java.util.List;

public enum Type {

    START(0),
    MOVE(2),
    STATUS(0),
    END(0),
    ;

    private final int parameterSize;

    Type(final int parameterSize) {
        this.parameterSize = parameterSize;
    }

    public boolean matchSize(final List<String> parameters) {
        return parameters.size() == parameterSize;
    }
}
