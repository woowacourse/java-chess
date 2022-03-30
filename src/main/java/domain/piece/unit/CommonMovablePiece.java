package domain.piece.unit;

import domain.piece.property.PieceInfo;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.piece.property.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonMovablePiece extends AbstractPiece {

    public CommonMovablePiece(final PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    protected List<Position> calculateAvailableDirectionByPosition(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        int x = source.getXPosition() + direction.getXPosition();
        int y = source.getYPosition() + direction.getYPosition();

        while (checkOverRange(x, y)) {
            positions.add(Position.of(XPosition.of(x), YPosition.of(y)));
            x += direction.getXPosition();
            y += direction.getYPosition();
        }
        return positions;
    }

    @Override
    public boolean checkOneAndTwoSouthNorthDirections(Position target){
        return false;
    }
}
