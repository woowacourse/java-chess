package chess.util;

import chess.domain.Position;

import java.util.Arrays;
import java.util.List;

public class MessagePositionConverter {

    private static final String MESSAGE_SPLIT_REGEX = " ";
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private MessagePositionConverter() {
    }

    public static List<Position> convert(String message) {
        String[] splitPositions = message.split(MESSAGE_SPLIT_REGEX);
        Position currentPosition = StringPositionConverter
                .convertToPosition(splitPositions[CURRENT_POSITION_INDEX]);
        Position targetPosition = StringPositionConverter
                .convertToPosition(splitPositions[TARGET_POSITION_INDEX]);
        return Arrays.asList(currentPosition, targetPosition);
    }
}
