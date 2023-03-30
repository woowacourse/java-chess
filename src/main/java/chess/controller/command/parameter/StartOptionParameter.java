package chess.controller.command.parameter;

import chess.exception.ChessException;
import chess.exception.ExceptionCode;

public class StartOptionParameter extends CommandParameter {

    public static final String NEW_GAME_OPTION = "new";
    private static final String ROOM_ID_REGEX = "\\d+";

    private StartOptionParameter(final String value) {
        super(value);
    }

    public static CommandParameter of(final String value) {
        validateValue(value);
        return new StartOptionParameter(value);
    }

    private static void validateValue(final String value) {
        if (value.matches(NEW_GAME_OPTION) || value.matches(ROOM_ID_REGEX)) {
            return;
        }
        throw new ChessException(ExceptionCode.INVALID_COMMAND_PARAMETER);
    }
}
