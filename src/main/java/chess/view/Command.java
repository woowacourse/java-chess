package chess.view;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chess.controller.ChessController;
import chess.domain.board.Position;
import chess.exceptions.InvalidInputException;

public enum Command {
    START("start", ChessController::quit),
    END("end", ChessController::initialize),
    STATUS("status", ChessController::showStatus),
    MOVE("move ([a-h][1-8]) ([a-h][1-8])", ChessController::move);

    private final String value;
    private Consumer<String> consumer;

    Command(final String value, Consumer<String> consumer) {
        this.value = value;
        this.consumer = consumer;
    }

    public boolean is(String value) {
        return value.matches(this.value);
    }

    public void accept(String input) {
        consumer.accept(input);
    }

    public Position startPositionOf(String input) {
        Pattern pattern = Pattern.compile(MOVE.value);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Position.of(matcher.group(1));
        }
        throw new InvalidInputException();
    }

    public Position endPositionOf(String input) {
        Pattern pattern = Pattern.compile(MOVE.value);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Position.of(matcher.group(2));
        }
        throw new InvalidInputException();
    }

    public static void validate(String value) {
        Arrays.stream(Command.values())
            .filter(command -> command.is(value))
            .findAny()
            .orElseThrow(InvalidInputException::new);
    }

    public String getValue() {
        return value;
    }
}
