package chess.model.position;

import chess.model.piece.Piece;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Route {

    private static final int REMAIN_THRESHOLD = 1;

    private final Set<Position> positions;

    private Route(Set<Position> positions) {
        this.positions = positions;
    }

    public static Route of(Position source, Position target) {
        int columnOffset = source.calculateColumnOffSet(target);
        int rowOffset = source.calculateRowOffSet(target);

        Set<Position> positions = new HashSet<>();
        while (isRemain(rowOffset) || isRemain(columnOffset)) {
            columnOffset = consumeOffset(columnOffset);
            rowOffset = consumeOffset(rowOffset);
            positions.add(nextPosition(source, columnOffset, rowOffset));
        }
        return new Route(positions);
    }

    public static Route empty() {
        return new Route(Set.of());
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

    public boolean containsAll(Set<Position> positions) {
        return this.positions.containsAll(positions);
    }
}
