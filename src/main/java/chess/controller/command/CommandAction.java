package chess.controller.command;

import java.util.List;
import java.util.function.Consumer;

public abstract class CommandAction {
    public static final Consumer<List<String>> EMPTY_ACTION = ignored -> {};
    protected final Consumer<List<String>> consumer;

    public CommandAction(final Consumer<List<String>> consumer) {
        this.consumer = consumer;
    }

    public abstract boolean isRunnable();

    public abstract void execute(final Command command);
}
