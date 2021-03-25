package domain.piece;

import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Knight extends Piece {

    private static final String NAME = "n";
    private static final Score SCORE = new Score(2.5);

    public Knight(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    public static Map<Position, Piece> createInitialKnights() {
        Map<Position, Piece> initialKnights = new HashMap<>();
        initialKnights.put(new Position("b8"), new Knight(true));
        initialKnights.put(new Position("g8"), new Knight(true));
        initialKnights.put(new Position("b1"), new Knight(false));
        initialKnights.put(new Position("g1"), new Knight(false));
        return initialKnights;
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        return Direction.knightDirection()
            .stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

}
