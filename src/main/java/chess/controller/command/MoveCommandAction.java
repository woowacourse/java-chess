package chess.controller.command;

import java.util.List;
import java.util.function.Consumer;

public class MoveCommandAction extends CommandAction {
    public MoveCommandAction(final Consumer<List<String>> consumer) {
        super(consumer);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public void execute(final Command command) {
        consumer.accept(command.getParameters());
    }
}
