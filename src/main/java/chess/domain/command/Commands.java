package chess.domain.command;

import chess.domain.Game;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Commands {
    START("start", StartCommand::new),
    MOVE("move ([a-h][1-8]) ([a-h][1-8])", MoveCommand::new),
    END("end", EndCommand::new),
    STATUS("status", StatusCommand::new);

    private final String message;
    private final Function<Game, Command> command;

    Commands(String message, Function<Game, Command> command) {
        this.message = message;
        this.command = command;
    }

    public static Command of(String value, Game game) {
        return Stream.of(values())
            .filter(command -> command.is(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."))
            .command.apply(game);
    }

    private boolean is(String value) {
        return value.matches(this.message);
    }

    public String getMessage() {
        return message;
    }
}
