package chess.view;

import chess.controller.command.Command;
import chess.controller.command.End;
import chess.controller.command.Move;
import chess.controller.command.Start;
import chess.controller.command.Status;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandMapper {

    START("start", text -> Start.getInstance()),
    END("end", text -> End.getInstance()),
    STATUS("status", text -> Status.getInstance()),
    MOVE("move", CommandMapper::toMove),
    ;

    private static final String ERROR_MESSAGE = "잘못된 명령입니다.";
    private static final String MOVE_COMMAND_FORMAT_ERROR = "move source위치 target위치 형식이 아닙니다.";
    private static final String SPLIT_DELIMITER = " ";
    private static final int SPLIT_TEXT_LENGTH = 3;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    private final String value;
    private final Function<String, Command> function;

    CommandMapper(final String value, final Function<String, Command> function) {
        this.value = value;
        this.function = function;
    }

    public static Command from(String text) {
        return Arrays.stream(values())
                .filter(it -> text.startsWith(it.value))
                .map(it -> it.function.apply(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    private static Move toMove(final String text) {
        final List<String> splitText = toList(text);
        return new Move(splitText.get(FROM_POSITION_INDEX), splitText.get(TO_POSITION_INDEX));
    }

    private static List<String> toList(final String text) {
        final String[] splitText = text.split(SPLIT_DELIMITER);
        if (splitText.length != SPLIT_TEXT_LENGTH) {
            throw new IllegalArgumentException(MOVE_COMMAND_FORMAT_ERROR);
        }
        return List.of(splitText);
    }
}
