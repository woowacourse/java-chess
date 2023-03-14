package chess.domain;

public class BishopMoveRule implements MoveRule {
    @Override
    public Position move(Position currentPosition, Position nextPosition) {
        if (currentPosition.isDiagonalEqual(nextPosition)) {
            return nextPosition;
        }
        throw new IllegalArgumentException("비숍은 대각선상으로만 움직일 수 있습니다.");
    }
}
