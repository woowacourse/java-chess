package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class MovableRangePiece extends Piece {

    public MovableRangePiece(Player player, PieceSymbol pieceSymbol) {
        super(player, pieceSymbol);
    }

    @Override
    protected List<Position> calculateAvailablePosition(final Position source,
        final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position startPosition = source;
        while (checkOverRange(startPosition, direction)) {
            Position nextPosition = createPositionByDirection(startPosition, direction);
            positions.add(nextPosition);
            startPosition = nextPosition;
        }
        return positions;
    }
}
