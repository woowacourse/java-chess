package chess.dto;

import chess.domain.board.position.Column;
import chess.domain.board.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {
    public static List<String> getAblePositions() {
        List<String> positions = new ArrayList<>();
        for (Column column : Column.values()) {
            addPositionOfColumn(positions, column);
        }
        return positions;
    }

    private static void addPositionOfColumn(List<String> positions, Column column) {
        for (Rank rank : Rank.values()) {
            positions.add(new PositionDto(column, rank).get());
        }
    }
}
