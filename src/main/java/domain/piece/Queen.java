package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece {

    private static final String NAME = "q";
    private static final Score SCORE = new Score(9);

    public Queen(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    public static Map<Position, Piece> createInitialQueens() {
        Map<Position, Piece> initialQueens = new HashMap<>();
        initialQueens.put(new Position("d8"), new Queen(true));
        initialQueens.put(new Position("d1"), new Queen(false));
        return initialQueens;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        if (source.isNotLinearPosition(target) && source.isNotDiagonalPosition(target)) {
            return false;
        }
        Direction direction = targetDirection(source, target);
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.piece(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }

    private Direction targetDirection(Position source, Position target) {
        if (!source.isNotLinearPosition(target)) {
            return Direction.linearTargetDirection(source.difference(target));
        }
        return Direction.diagonalTargetDirection(source.difference(target));
    }

}
