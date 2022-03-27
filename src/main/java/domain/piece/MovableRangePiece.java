package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class MovableRangePiece extends Piece {

    public MovableRangePiece(Player player, PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        super(player, pieceSymbol, directionsGenerator);
    }

    @Override
    protected List<Position> calculateAvailablePosition(final Position source,
        final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position startPosition = source;
        while (checkOverRange(startPosition, direction)) {
            Position nextPosition = createDirectionPosition(startPosition, direction);
            positions.add(nextPosition);
            startPosition = nextPosition;
        }
        return positions;
    }
}
