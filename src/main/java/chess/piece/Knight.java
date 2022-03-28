package chess.piece;

import chess.position.Direction;
import chess.position.Position;
import chess.utils.PossiblePositionChecker;

import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        return List.of();
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return PossiblePositionChecker.isMovableCoordinates(Direction.knight(), source, target);
    }
}