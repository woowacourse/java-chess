package chess.controller.command;

public enum CommandType {
    START(0),
    MOVE(2),
    END(0),
    ;

    private final int numberOfParameters;

    CommandType(final int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
