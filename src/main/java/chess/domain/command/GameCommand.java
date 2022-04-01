package chess.domain.command;

import chess.ChessGame;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public enum GameCommand {

    START("start", Start::new),
    MOVE("move [a-h][1-9] [a-h][1-9]", Move::new),
    END("end", End::new),
    STATUS("status", Status::new),
    ;

    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";

    private final String command;
    private final Supplier<CommandStrategy> commandGenerator;

    GameCommand(String command, Supplier<CommandStrategy> commandGenerator) {
        this.command = command;
        this.commandGenerator = commandGenerator;
    }

    public static GameCommand from(final String command) {
        return Arrays.stream(GameCommand.values())
            .filter(it -> Pattern.matches(it.command, command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }

    public void execute(final String command, final ChessGame chessGame, final Runnable runnable) {
        commandGenerator.get().execute(command, chessGame, runnable);
    }
}
