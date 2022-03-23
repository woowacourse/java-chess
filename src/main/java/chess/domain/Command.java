package chess.domain;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (input, chessGame) -> chessGame.start()),
    END("end", (input, chessGame) -> chessGame.end()),
    ;

    private final String name;
    private final BiConsumer<String, ChessGame> operate;

    Command(final String name, final BiConsumer<String, ChessGame> operate) {
        this.name = name;
        this.operate = operate;
    }

    public static void execute(final String input, final ChessGame chessGame) {
        Arrays.stream(Command.values())
                .filter(command -> input.contains(command.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어"))
                .operate
                .accept(input, chessGame);
    }
}
