package chess.controller.dto;

import chess.domain.Position;

public class PositionDto {

    private final String position;

    public PositionDto(Position position) {
        char row = (char) ('a' + position.row());
        StringBuilder rowAndColumn = new StringBuilder();
        rowAndColumn.append(row)
                .append(position.column() + 1);
        this.position = rowAndColumn.toString();
    }

    public String getPosition() {
        return position;
    }
}
