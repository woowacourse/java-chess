package domain.piece;

import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.Map;

public class Rook extends Piece {

    private static final Score SCORE = new Score(5);
    private static final String NAME = "r";

    public Rook(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        if (source.isNotLinearPosition(target)) {
            return false;
        }
        Direction direction = Direction.linearTargetDirection(source.difference(target));
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.get(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }

}
