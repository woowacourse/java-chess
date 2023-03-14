package chess.domain;

public class RookMoveRule implements MoveRule {
    @Override
    public Position move(Position currentPosition, Position nextPosition) {
        if (currentPosition.isStraightEqual(nextPosition)){
            return nextPosition;
        }
        throw new IllegalArgumentException("룩은 직선상으로만 움직일 수 있습니다.");
    }

}
