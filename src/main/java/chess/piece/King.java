package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.Set;

public class King {


    private final Set<Movement> movements = Set.of(Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT);

    public Set<Position> canMove(Position from, Position to) {
        Set<Position> positions = from.findMoveAblePositions(movements);
        System.out.println(positions);
        if (!positions.contains(to)) {
            throw new IllegalArgumentException("애초에 니 못감 ㅅㄱㅇ");
        }
        return Set.of();
    }


}
