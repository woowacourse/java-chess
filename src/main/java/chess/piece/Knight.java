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
    public boolean isMovable(Position source, Position target) {
        return PossiblePositionChecker.isMovablePositions(Direction.knight(), source, target);
    }

    @Override
    public List<Position> computeBetweenTwoPositionByLine(Position source, Position target) {
        return List.of();
    }
}