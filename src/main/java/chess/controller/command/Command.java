package chess.controller.command;

import chess.domain.chessGame.ChessGame;

import java.util.Arrays;
import java.util.function.Function;

public enum Command {
    START("start", StartCommandExecute::new),
    MOVE("move", MoveCommandExecute::new),
    END("end", EndCommandExecute::new),
    STATUS("status", StatusCommandExecute::new);

    private final String command;
    private final Function<ChessGame, CommandExecute> executorGenerator;

    Command(String command, Function<ChessGame, CommandExecute> executorGenerator) {
        this.command = command;
        this.executorGenerator = executorGenerator;
    }

    public static Command findCommand(String commandType) {
        return Arrays.stream(Command.values())
                .filter(c -> c.command.equals(commandType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어가 아닙니다."));
    }

    public CommandExecute generateExecutor(ChessGame chessGame) {
        return this.executorGenerator.apply(chessGame);
    }
}
