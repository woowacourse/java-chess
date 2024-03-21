package domain;

import java.util.List;

public class EndCommand extends Command {
    public static EndCommand END_COMMAND = new EndCommand();

    private EndCommand() {
        this(null);
    }

    private EndCommand(String[] options) {
        super(options);
    }

    @Override
    public <T> List<T> getOptions() {
        throw new UnsupportedOperationException();
    }
}
