package chess.util;

import chess.domain.Position;

public class MessagePositionConverter {

    private String[] positions;
    private StringPositionConverter stringPositionConverter;

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
