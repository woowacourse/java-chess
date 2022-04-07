package chess.dto;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;

public class PositionDto {
    private final Position position;

    public PositionDto(Position position) {
        this.position = position;
    }

    public PositionDto(Column column, Rank rank) {
        this.position = new Position(column, rank);
    }

    public String get() {
        return position.getColumn().name() + position.getRank().getNumber();
    }
}
