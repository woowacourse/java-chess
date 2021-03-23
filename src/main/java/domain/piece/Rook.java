package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Rook extends Piece {

    private static final Score SCORE = new Score(5);
    private static final String NAME = "r";

    public Rook(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    public static Map<Position, Piece> createInitialRook() {
        Map<Position, Piece> initialRook = new HashMap<>();
        initialRook.put(new Position("a8"), new Rook(true));
        initialRook.put(new Position("h8"), new Rook(true));
        initialRook.put(new Position("a1"), new Rook(false));
        initialRook.put(new Position("h1"), new Rook(false));
        return initialRook;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        if (!target.isChessBoardPosition() || isSameColor(board.piece(target))
            || source.isNotLinearPosition(target)) {
            return false;
        }
        Direction direction = Direction.linearTargetDirection(source.diff(target));
        do {
            source = source.sum(direction);
        } while (!source.equals(target)
            && board.piece(source).isEmpty() && source.isChessBoardPosition());
        return source.equals(target);
    }

}
