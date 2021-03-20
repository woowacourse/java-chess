package chess.util;

import chess.domain.Position;

public final class MessagePositionConverter {

    private static final String MESSAGE_SPLIT_REGEX = " ";
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final String[] positions;
    private final StringPositionConverter stringPositionConverter;

    public MessagePositionConverter(String message) {
        positions = message.split(MESSAGE_SPLIT_REGEX);
        stringPositionConverter = new StringPositionConverter();
    }

    public Position currentPosition() {
        return stringPositionConverter.convert(positions[CURRENT_POSITION_INDEX]);
    }

    public Position targetPosition() {
        return stringPositionConverter.convert(positions[TARGET_POSITION_INDEX]);
    }
}
