package chess.domain.command;

import chess.domain.manager.ChessManager;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum CommandMenu {
    START("start", Start::of),
    RESTART("restart", Restart::of),
    END("end", End::of),
    MOVE("move", Move::of),
    STATUS("status", Status::of),
    SHOW("show", Show::of);

    private static final int COMMAND_INDEX = 0;

    private final String command;
    private final BiFunction<ChessManager, List<String>, Command> biFunction;

    CommandMenu(final String command, final BiFunction<ChessManager, List<String>, Command> biFunction) {
        this.command = command;
        this.biFunction = biFunction;
    }

    public static Command findRunningCommandByInput(final ChessManager chessManager, final List<String> input) {
        return Arrays.stream(values())
                .filter(commandMenu -> commandMenu.command.equalsIgnoreCase(input.get(COMMAND_INDEX)))
                .filter(commandMenu -> !START.command.equalsIgnoreCase(input.get(COMMAND_INDEX)))
                .map(commandMenu -> commandMenu.biFunction.apply(chessManager, input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어입니다."));
    }

    public static Command findFirstCommandByInput(
            final ChessManager chessManager, final List<String> input) {
        final String command = input.get(COMMAND_INDEX);
        return Arrays.stream(values())
                .filter(commandMenu -> END.command.equalsIgnoreCase(command) || START.command.equalsIgnoreCase(command))
                .map(commandMenu -> commandMenu.biFunction.apply(chessManager, input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 시작 전에는 start(게임시작) 또는 end(게임 끝)만 가능합니다."));
    }
}
