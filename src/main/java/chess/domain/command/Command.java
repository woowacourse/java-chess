package chess.domain.command;

import chess.domain.chessGame.ChessGameState;

import java.util.Arrays;
import java.util.function.Function;

public enum Command {
    START("start", StartCommandExecute::new),
    MOVE("move", MoveCommandExecute::new),
    END("end", EndCommandExecute::new);

    private final String command;
    private final Function<ChessGameState, CommandExecute> executorGenerator;

    Command(String command, Function<ChessGameState, CommandExecute> executorGenerator) {
        this.command = command;
        this.executorGenerator = executorGenerator;
    }

    public static Command parseCommand(String commandHead) {
        return Arrays.stream(Command.values())
                .filter(c -> c.command.equals(commandHead))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어가 아닙니다."));
    }

    public CommandExecute generate(ChessGameState chessGameState) {
        return this.executorGenerator.apply(chessGameState);
    }
}
