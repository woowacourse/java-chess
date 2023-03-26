package chess.mapper;

import chess.domain.position.Position;

public final class PositionStringMapper {

    private PositionStringMapper() {
    }

    public static String mapping(Position position) {
        return position.getFile() + position.getRank().getValue();
    }
}
