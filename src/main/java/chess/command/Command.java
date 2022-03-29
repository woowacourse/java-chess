package chess.command;

import chess.domain.ChessGame;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Command {

    private static final String ERROR_MESSAGE = "잘못된 명령입니다.";
    private static final String SPLIT_DELIMITER = " ";
    private static final int SPLIT_TEXT_LENGTH = 3;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    public static Command from(final String text) {
        return Value.of(text).orElseThrow(() ->
                new IllegalArgumentException(ERROR_MESSAGE));
    }

    public abstract void execute(ChessGame chessGame);

    private enum Value {

        START("start", text -> Start.getInstance()),
        END("end", text -> End.getInstance()),
        STATUS("status", text -> Status.getInstance()),
        MOVE("move", text -> {
            final List<String> splitText = toList(text);
            return new Move(splitText.get(FROM_POSITION_INDEX), splitText.get(TO_POSITION_INDEX));
        }),
        ;

        private final String value;
        private final Function<String, Command> function;

        Value(final String value, final Function<String, Command> function) {
            this.value = value;
            this.function = function;
        }

        private static List<String> toList(final String text) {
            final String[] splitText = text.split(SPLIT_DELIMITER);
            if (splitText.length != SPLIT_TEXT_LENGTH) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            return List.of(splitText);
        }

        private static Optional<Command> of(String text) {
            return Arrays.stream(values())
                    .filter(it -> text.startsWith(it.value))
                    .map(it -> it.function.apply(text))
                    .findFirst();
        }
    }
}
