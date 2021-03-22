package domain.piece;

import domain.Score;
import domain.board.Board;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece {

    private static final Score SCORE = new Score(9);
    private static final String NAME = "q";

    public Queen(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        if (!target.isChessBoardPosition() || isSameColor(board.piece(target))
            || (source.isNotLinearPosition(target) && source.isNotDiagonalPosition(target))) {
            return false;
        }
        Direction direction = targetDirection(source, target);
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.piece(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }

    public String getName() {
        return NAME;
    }

    public Score score() {
        return SCORE;
    }

    public static Map<Position, Piece> createInitialQueen() {
        Map<Position, Piece> initialQueen = new HashMap<>();
        initialQueen.put(new Position("d8"), new Queen(true));
        initialQueen.put(new Position("d1"), new Queen(false));
        return initialQueen;
    }

    private Direction targetDirection(Position source, Position target) {
        if (!source.isNotLinearPosition(target)) {
            return Direction.linearTargetDirection(source.diff(target));
        }
        return Direction.diagonalTargetDirection(source.diff(target));
    }
}
