package domain.piece;

import domain.chessgame.Score;
import domain.board.Board;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Bishop extends Piece {

    private static final Score SCORE = new Score(3);
    private static final String NAME = "b";

    public Bishop(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        if (!target.isChessBoardPosition() || isSameColor(board.piece(target))
            || source.isNotDiagonalPosition(target)) {
            return false;
        }
        Direction direction = Direction.diagonalTargetDirection(source.diff(target));
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.piece(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }

    public static Map<Position, Piece> createInitialBishop() {
        Map<Position, Piece> initialBishop = new HashMap<>();
        initialBishop.put(new Position("c8"), new Bishop(true));
        initialBishop.put(new Position("f8"), new Bishop(true));
        initialBishop.put(new Position("c1"), new Bishop(false));
        initialBishop.put(new Position("f1"), new Bishop(false));
        return initialBishop;
    }
}
