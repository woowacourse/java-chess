package controller.command;

import domain.game.ChessGame;
import java.util.Arrays;
import java.util.function.Function;
import view.command.CommandType;

public enum Command {
    START(StartCommandExecutor::new),
    END(EndCommandExecutor::new),
    MOVE(MoveCommandExecutor::new);

    private final Function<CommandType, CommandExecutor> executorFunction;

    Command(final Function<CommandType, CommandExecutor> executorFunction) {
        this.executorFunction = executorFunction;
    }

    public static Command from(final CommandType commandType) {
        return Arrays.stream(Command.values())
                .filter(command -> command.name().equals(commandType.name()))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    public void execute(final CommandType commandType, final ChessGame chessGame) {
        executorFunction.apply(commandType).execute(chessGame);
    }
}
