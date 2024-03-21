package domain.chess.game;

import java.util.List;

public class StartCommand extends Command {
    public static StartCommand START_COMMAND = new StartCommand();

    private StartCommand() {
        this(null);
    }

    private StartCommand(String[] options) {
        super(options);
    }

    @Override
    public <T> List<T> getOptions() {
        throw new UnsupportedOperationException();
    }
}
