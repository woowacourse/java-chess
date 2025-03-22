package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WhitePawn {
    private static Set<Movement> movements = new HashSet<>(Collections.singleton(Movement.UP));
    public static Set<Movement> movementsEat = Set.of(Movement.RIGHT_UP, Movement.LEFT_UP);

    public static Set<Position> canMove(Position from, Position to) {
        if (from.isStartWhitePawn()) {
            movements.add(Movement.UP_UP);
        }
        Set<Position> positions = from.findMoveAblePositions(movements);

        if (!positions.contains(to)) {
            throw new IllegalArgumentException("애초에 니 못감 ㅅㄱㅇ");
        }
        return positions;
    }

    public static Set<Position> canEat(Position from, Position to) {

        Set<Position> positions = from.findMoveAblePositions(movementsEat);

        return positions;
    }
}
