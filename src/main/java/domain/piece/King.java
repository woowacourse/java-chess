package domain.piece;

import domain.Score;
import domain.board.Board;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class King extends Piece {

    private static final Score SCORE = new Score(0);
    private static final String NAME = "k";

    public King(boolean isBlack) {
        super(isBlack);
    }

    public static Map<Position, Piece> createInitialKing() {
        Map<Position, Piece> initialKing = new HashMap<>();
        initialKing.put(new Position("e8"), new King(true));
        initialKing.put(new Position("e1"), new King(false));
        return initialKing;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        if (!target.isChessBoardPosition() || isSameColor(board.piece(target))) {
            return false;
        }
        return Direction.everyDirection()
            .stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

    public String getName() {
        return NAME;
    }

    public Score score() {
        return SCORE;
    }

}
