package chess.util;

import chess.domain.Position;

public final class MessagePositionConverter {

    private final String[] positions;
    private final StringPositionConverter stringPositionConverter;

    public MessagePositionConverter(String message) {
        positions = message.split(" ");
        stringPositionConverter = new StringPositionConverter();
    }

    public Position currentPosition() {
        return stringPositionConverter.convert(positions[1]);
    }

    public Position targetPosition() {
        return stringPositionConverter.convert(positions[2]);

    }
}
