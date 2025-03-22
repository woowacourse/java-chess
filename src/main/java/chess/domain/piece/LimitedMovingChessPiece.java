package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public abstract class LimitedMovingChessPiece implements ChessPiece {

    protected final List<Movement> movements;
    protected final Color side;
    protected boolean isCaptured;

    public LimitedMovingChessPiece(List<Movement> movements, Color side) {
        this.movements = movements;
        this.side = side;
        this.isCaptured = false;
    }

    @Override
    public void validateCanMove(Position origin, Position destination) {
        boolean canMove = false;
        for (Movement movement : movements) {
            if (origin.move(movement).equals(destination)) {
                canMove = true;
                break;
            }
        }
        if (!canMove) {
            throw new IllegalStateException("해당 기물은 해당 경로로 이동할 수 없습니다.");
        }
    }

    @Override
    public List<Movement> findRoute(Position origin, Position destination) {
        for (Movement movement : movements) {
            if (origin.move(movement).equals(destination)) {
                return List.of(movement);
            }
        }
        return List.of();
    }

    @Override
    public void move() {

    }

    @Override
    public Color getColor() {
        return side;
    }

    @Override
    public void capture() {
        this.isCaptured = true;
    }
}
