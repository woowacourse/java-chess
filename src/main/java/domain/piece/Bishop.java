package domain.piece;

import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.Map;

public class Bishop extends Piece {

    private static final String NAME = "b";
    private static final Score SCORE = new Score(3);

    public Bishop(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        if (source.isNotDiagonalPosition(target)) {
            return false;
        }
        Direction direction = Direction.diagonalTargetDirection(source.difference(target));
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.get(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }

}
