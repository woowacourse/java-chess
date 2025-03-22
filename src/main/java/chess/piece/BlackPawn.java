package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.HashSet;
import java.util.Set;

public class BlackPawn {
    private static Set<Movement> movements = new HashSet<>(Set.of(Movement.DOWN));

    private static Set<Movement> movementsEat = Set.of(Movement.LEFT_DOWN, Movement.RIGHT_DOWN);

    public static Set<Position> canMove(Position from, Position to) {
        if (from.isStartWithBlackPawn()) {
            movements.add(Movement.DOWN_DOWN);
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
