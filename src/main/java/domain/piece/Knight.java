package domain.piece;

import domain.Score;
import domain.board.Board;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Knight extends Piece {

    private static final Score SCORE = new Score(2.5);
    private static final String NAME = "k";

    public Knight(boolean isBlack) {
        super(isBlack);
    }

    public String getName() {
        return NAME;
    }

    public Score score() {
        return SCORE;
    }

    public static Map<Position, Piece> createInitialKnight() {
        Map<Position, Piece> initialKnight = new HashMap<>();
        initialKnight.put(new Position("b8"), new Knight(true));
        initialKnight.put(new Position("g8"), new Knight(true));
        initialKnight.put(new Position("b1"), new Knight(false));
        initialKnight.put(new Position("g1"), new Knight(false));
        return initialKnight;
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
        return Direction.knightDirection()
            .stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

}
