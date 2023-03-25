package chess.dto;

import chess.domain.board.Position;

public final class PositionStringMapper {

    private PositionStringMapper() {
    }

    public static String mapping(Position position) {
        return position.getFile() + position.getRank().getValue();
    }
}
