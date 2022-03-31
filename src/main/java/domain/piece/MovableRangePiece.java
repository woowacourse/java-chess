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
    protected List<Position> calculateAvailablePosition(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position nextPosition = createPositionByDirection(source, direction);
        while (nextPosition != null) {
            positions.add(nextPosition);
            nextPosition = createPositionByDirection(nextPosition, direction);
        }
        return positions;
    }
}
