package chess.domain;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Command {

    START("start", ChessGame::start),
    END("end", ChessGame::end);

    private final String name;
    private final Consumer<ChessGame> consumer;

    Command(final String name, final Consumer<ChessGame> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public static Command findByString(final String name) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }

    public void execute(final ChessGame chessGame) {
        this.consumer.accept(chessGame);
    }
}
