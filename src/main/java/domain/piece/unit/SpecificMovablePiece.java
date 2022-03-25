package domain.piece.unit;

import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificMovablePiece extends Piece {

    public SpecificMovablePiece(final Team Team, final PieceSymbol unit) {
        super(Team, unit);
    }

    @Override
    protected void calculateAvailableDirectionalPositions(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        int x = source.getX() + direction.getX();
        int y = source.getY() + direction.getY();

        if (checkOverRange(x, y)) {
            positions.add(Position.of(XPosition.of(x), YPosition.of(y)));
        }
        addDirectionalPosition(direction, positions);
    }
}
