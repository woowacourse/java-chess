package chess.model;

import chess.model.piece.Piece;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Route {

    private static final int REMAIN_THRESHOLD = 1;

    private final List<Position> positions;

    private Route(List<Position> positions) {
        this.positions = positions;
    }

    public static Route of(Position source, Position target) {
        int columnOffset = source.calculateColumnOffSet(target);
        int rowOffset = source.calculateRowOffSet(target);

        List<Position> positions = new ArrayList<>();
        while (isRemain(rowOffset) || isRemain(columnOffset)) {
            columnOffset = consumeOffset(columnOffset);
            rowOffset = consumeOffset(rowOffset);
            positions.add(nextPosition(source, columnOffset, rowOffset));
        }
        return new Route(positions);
    }

    private static boolean isRemain(int offset) {
        return Math.abs(offset) > REMAIN_THRESHOLD;
    }

    private static int consumeOffset(int offset) {
        if (offset > 0) {
            offset--;
        }
        if (offset < 0) {
            offset++;
        }
        return offset;
    }

    private static Position nextPosition(Position source, int columnOffset, int rowOffset) {
        Column sourceColumn = source.getColumn();
        Row sourceRow = source.getRow();
        return new Position(sourceColumn.add(columnOffset), sourceRow.add(rowOffset));
    }

    public boolean isBlocked(Map<Position, Piece> pieces) {
        return positions.stream()
            .anyMatch(position -> isPieceExist(pieces, position));
    }

    private boolean isPieceExist(Map<Position, Piece> pieces, Position position) {
        Piece piece = pieces.get(position);
        return piece.isExist();
    }
}
