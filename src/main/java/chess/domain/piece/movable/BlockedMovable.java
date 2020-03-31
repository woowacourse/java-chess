package chess.domain.piece.movable;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;
import java.util.List;
public class BlockedMovable implements Movable {
    private final Directions moveDirections;
    public BlockedMovable(Directions moveDirections) {
        this.moveDirections = moveDirections;
    }
    @Override
    public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
        Positions movablePositions = Positions.create();
        for (Direction direction : moveDirections.getDirections()) {
            Positions positions = createMovablePositionsByDirection(position, direction, pieces, color);
            movablePositions.addAll(positions);
        }
        return movablePositions;
    }
    Positions createMovablePositionsByDirection(Position movablePosition, Direction direction, List<Piece> pieces, Color color) {
        Positions movablePositions = Positions.create();
        while (isOpen(movablePosition, direction, pieces, color)) {
            movablePosition = movablePosition.getMovedPositionBy(direction);
            movablePositions.add(movablePosition);
        }
        return movablePositions;
    }
    private boolean isOpen(Position movablePosition, Direction direction, List<Piece> pieces, Color color) {
        if (!movablePosition.checkBound(direction)) {
            return false;
        }
        if (isPossessedByDifferentColor(movablePosition, pieces, color)) {
            return false;
        }
        return !isPossessedBySameColor(movablePosition.getMovedPositionBy(direction), pieces, color);
    }
    private boolean isPossessedByDifferentColor(Position position, List<Piece> pieces, Color color) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
    }
    private boolean isPossessedBySameColor(Position position, List<Piece> pieces, Color color) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
    }
}