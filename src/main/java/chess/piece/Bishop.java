package chess.piece;


import chess.Movement;
import chess.Position;
import java.util.Set;

public class Bishop {


    private static Set<Movement> movements = Set.of(Movement.LEFT_UP, Movement.LEFT_DOWN, Movement.RIGHT_UP,
            Movement.RIGHT_DOWN);

    public static Set<Position> canMove(Position from, Position to) {
        Set<Position> positions = from.findSlidingAblePositions(movements);
        if (!positions.contains(to)) {
            throw new IllegalArgumentException("애초에 니 못감 ㅅㄱㅇ");
        }
        return positions;
    }

    public static Set<Position> betweenPosition(Position from, Position to) {
        Set<Position> betweenSlidingAblePositions = from.findBetweenSlidingAblePositions(movements, to);
        return betweenSlidingAblePositions;
    }


}
