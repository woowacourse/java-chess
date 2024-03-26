package domain.piece;

import static domain.position.Movement.LEFT_DOWN;
import static domain.position.Movement.LEFT_UP;
import static domain.position.Movement.RIGHT_DOWN;
import static domain.position.Movement.RIGHT_UP;

import domain.position.Movement;
import domain.position.Position;
import java.util.Set;

public class Bishop extends Piece {

    private static final Set<Movement> VALID_MOVEMENTS = Set.of(RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);

    public Bishop(Color color) {
        super(color);
    } // TODO: 자동완성 단축키, 터미널 단축키 변경, 탭 넘기기

    @Override
    public boolean canMove(Position source, Position target) {
        Movement movement = Movement.asMovement(source, target);
        return VALID_MOVEMENTS.contains(movement);
    }
}
