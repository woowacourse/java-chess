package domain.piece;

import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.Map;

public class Queen extends Piece {

    private static final String NAME = "q";
    private static final Score SCORE = new Score(9);

    public Queen(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    private Direction targetDirection(Position source, Position target) {
        if (!source.isNotLinearPosition(target)) {
            return Direction.linearTargetDirection(source.difference(target));
        }
        return Direction.diagonalTargetDirection(source.difference(target));
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        if (source.isNotLinearPosition(target) && source.isNotDiagonalPosition(target)) {
            return false;
        }
        Direction direction = targetDirection(source, target);
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.get(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }
}
