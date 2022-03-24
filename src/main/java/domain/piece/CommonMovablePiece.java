package domain.piece;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;

public abstract class CommonMovablePiece extends Piece {

    public CommonMovablePiece(TeamColor teamColor, PieceSymbol unit) {
        super(teamColor, unit);
    }

    @Override
    protected void calculateAvailablePosition(final Position source, final Direction direction) {
        int x = source.getX() + direction.getX();
        int y = source.getY() + direction.getY();

        while (checkOverRange(x, y)) {
            positionAdd(new Position(XPosition.of(x), YPosition.of(y)));
            x += direction.getX();
            y += direction.getY();
        }
    }
}
