package chess.domain;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (input, chessGame) -> chessGame.start()),
    END("end", (input, chessGame) -> chessGame.end()),
    ;

    private final String command;
    private final BiConsumer<String, ChessGame> operate;

    Command(final String command, final BiConsumer<String, ChessGame> operate) {
        this.command = command;
        this.operate = operate;
    }

    public static void execute(final String input, final ChessGame chessGame) {
        Arrays.stream(Command.values())
                .filter(command -> input.contains(command.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어 입니다."))
                .operate
                .accept(input, chessGame);
    }
}
