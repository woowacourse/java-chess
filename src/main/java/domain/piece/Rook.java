package domain.piece;

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

    public static Map<Position, Piece> createInitialRooks() {
        Map<Position, Piece> initialRooks = new HashMap<>();
        initialRooks.put(new Position("a8"), new Rook(true));
        initialRooks.put(new Position("h8"), new Rook(true));
        initialRooks.put(new Position("a1"), new Rook(false));
        initialRooks.put(new Position("h1"), new Rook(false));
        return initialRooks;
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
