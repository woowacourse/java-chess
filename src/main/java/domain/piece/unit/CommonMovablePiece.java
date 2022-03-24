package domain.piece.unit;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonMovablePiece extends Piece {

    public CommonMovablePiece(final TeamColor teamColor, final PieceSymbol unit) {
        super(teamColor, unit);
    }

    @Override
    protected void calculateAvailablePosition(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        int x = source.getX() + direction.getX();
        int y = source.getY() + direction.getY();

        while (checkOverRange(x, y)) {
            positions.add(new Position(XPosition.of(x), YPosition.of(y)));
            x += direction.getX();
            y += direction.getY();
        }
        addDirectionalPosition(direction, positions);
    }
}
