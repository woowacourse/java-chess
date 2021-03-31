package chess.domain.converter;

import chess.domain.Position;

public final class StringPositionConverter {

    public StringPositionConverter() {
    }

    public Position convert(String value) {
        String[] position = value.split("");
        return Position.of(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
    }
}
