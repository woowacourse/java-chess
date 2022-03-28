package chess.piece;

import chess.position.Direction;
import chess.position.Position;
import chess.utils.PossiblePositionChecker;

import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return PossiblePositionChecker.isMovableCoordinates(Direction.king(), source, target);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        return List.of();
    }
}
