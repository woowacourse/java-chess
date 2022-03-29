package chess.domain.command;

import chess.domain.ChessGame;
import java.util.Arrays;
import java.util.Optional;

public abstract class Command {

    private static final String ERROR_MESSAGE = "잘못된 명령입니다.";
    private static final String SPLIT_DELIMITER = " ";

    public static Command from(final String text) {
        final Optional<Command> possibleCommand = Arrays.stream(Value.values())
                .filter(it -> it.value.equals(text))
                .map(it -> it.instance)
                .findFirst();
        if (possibleCommand.isPresent()) {
            return possibleCommand.get();
        }

        if (!text.startsWith("move")) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        final String[] splitText = text.split(SPLIT_DELIMITER);
        if (splitText.length != 3) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return new Move(splitText[1], splitText[2]);
    }

    public abstract void execute(ChessGame chessGame);

    private enum Value {

        START("start", Start.getInstance()),
        END("end", End.getInstance()),
        STATUS("status", Status.getInstance()),
        ;

        private final String value;
        private final Command instance;

        Value(final String value, final Command instance) {
            this.value = value;
            this.instance = instance;
        }
    }
}
