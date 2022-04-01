package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificLocationPiece extends Piece {

    public SpecificLocationPiece(final Player player, final PieceSymbol pieceSymbol) {
        super(player, pieceSymbol);
    }

    @Override
    protected List<Position> calculateAvailablePosition(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position nextPosition = createPositionByDirection(source, direction);
        if (nextPosition != null) {
            positions.add(createPositionByDirection(source, direction));
        }
        return positions;
    }
}
