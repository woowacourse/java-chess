package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class King extends Piece {

    private static final String NAME = "k";
    private static final Score SCORE = new Score(0);

    public King(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    public static Map<Position, Piece> createInitialKings() {
        Map<Position, Piece> initialKings = new HashMap<>();
        initialKings.put(new Position("e8"), new King(true));
        initialKings.put(new Position("e1"), new King(false));
        return initialKings;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        return Direction.everyDirection()
            .stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

    @Override
    public boolean isKing() {
        return true;
    }

}
