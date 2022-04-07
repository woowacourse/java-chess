package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            positions.add(getPositionString(column, rank));
        }
    }

    private static String getPositionString(Column column, Rank rank) {
        return column.name() + rank.getNumber();
    }
}
