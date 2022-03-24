package domain.piece;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;

public abstract class SpecificMovablePiece extends Piece {

    public SpecificMovablePiece(TeamColor teamColor, PieceSymbol unit) {
        super(teamColor, unit);
    }

    @Override
    protected void calculateAvailablePosition(final Position source, final Direction direction) {
        int x = source.getX() + direction.getX();
        int y = source.getY() + direction.getY();

        if (checkOverRange(x, y)) {
            positionAdd(new Position(XPosition.of(x), YPosition.of(y)));
        }
    }
}
