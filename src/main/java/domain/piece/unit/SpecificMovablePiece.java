package domain.piece.unit;

import domain.piece.property.PieceInfo;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.piece.property.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificMovablePiece extends AbstractPiece {

    public SpecificMovablePiece(final PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    protected void calculateAvailableDirectionPosition(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        int x = source.getXPosition() + direction.getX();
        int y = source.getYPosition() + direction.getY();

        if (checkOverRange(x, y)) {
            positions.add(Position.of(XPosition.of(x), YPosition.of(y)));
        }
        addDirectionalPosition(direction, positions);
    }
}
