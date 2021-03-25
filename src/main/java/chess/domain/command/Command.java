package chess.domain.command;

import chess.domain.ChessGame;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {
    START("start", (chessGame, commander) -> chessGame.start()),
    END("end", (chessGame, commander) -> chessGame.end()),
    MOVE("move", ChessGame::move),
    STATUS("status", (chessGame, commander) -> chessGame.status());

    private final String operation;
    private final BiConsumer<ChessGame, List<String>> commander;

    Command(String operation, BiConsumer<ChessGame, List<String>> commander) {
        this.operation = operation;
        this.commander = commander;
    }

    public static Command getByInput(String input) {

        return Arrays.stream(Command.values())
            .filter(command -> command.operation.equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령입니다."));
    }

    public void execute(ChessGame chessGame, List<String> arguments) {
        commander.accept(chessGame, arguments);
    }
}
