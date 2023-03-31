package chess.controller.command.parameter;

public abstract class CommandParameter {

    private final String value;

    protected CommandParameter(final String value) {
        this.value = value;
    }

    public final String getValue() {
        return value;
    }
}
