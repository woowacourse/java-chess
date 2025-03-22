package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public abstract class Pawn extends LimitedMovingChessPiece {

    protected boolean isFirstMove;

    public Pawn(List<Movement> movements, Color side) {
        super(movements, side);
        this.isFirstMove = true;
    }

    @Override
    public String name() {
        return "P";
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    @Override
    public void move() {

    }

    public List<Movement> findRoute(Position origin, Position destination) {
        for (Movement movement : movements) {
            if (origin.canMove(movement) && origin.move(movement).equals(destination)) {
                if (movement.isTwoTimeVerticalMove() && !isFirstMove) {
                    throw new IllegalStateException("폰은 처음 움직일 때만 두 칸 움직일 수 있습니다.");
                }
                return List.of(movement);
            }
        }
        return List.of();
    }

    public void isMoved() {
        this.isFirstMove = false;
    }
}
