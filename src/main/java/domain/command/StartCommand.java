package domain.command;

import java.util.List;

public class StartCommand extends Command {
    public static StartCommand START_COMMAND = new StartCommand();

    private StartCommand() {
        this(null);
    }

    private StartCommand(List<String> options) {
        super(options);
    }

    @Override
    public <T> List<T> getOptions() {
        throw new UnsupportedOperationException();
    }
}
