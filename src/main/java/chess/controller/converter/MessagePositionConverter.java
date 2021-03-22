package chess.controller.converter;

import chess.domain.Position;
import chess.domain.Positions;

public final class MessagePositionConverter {

    private static final String MESSAGE_SPLIT_REGEX = " ";
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private MessagePositionConverter() {
    }

    public static Positions convert(String message) {
        String[] splitPositions = message.split(MESSAGE_SPLIT_REGEX);
        Position currentPosition = StringPositionConverter
            .convert(splitPositions[CURRENT_POSITION_INDEX]);
        Position targetPosition = StringPositionConverter
            .convert(splitPositions[TARGET_POSITION_INDEX]);
        return new Positions(currentPosition, targetPosition);
    }
}
