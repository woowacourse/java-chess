package chess.controller.command.parameter;

import chess.exception.ChessException;
import chess.exception.ExceptionCode;

public class PositionParameter extends CommandParameter {

    private static final String POSITION_REGEX = "^[a-h][1-8]$";

    private PositionParameter(final String value) {
        super(value);
    }

    public static CommandParameter of(final String value) {
        validateValue(value);
        return new PositionParameter(value);
    }

    private static void validateValue(final String value) {
        if (!value.matches(POSITION_REGEX)) {
            throw new ChessException(ExceptionCode.INVALID_COMMAND_PARAMETER);
        }
    }
}
