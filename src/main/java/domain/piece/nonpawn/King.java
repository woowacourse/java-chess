package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class King extends NonPawn {
    private static final int MOVE_COUNT_LIMIT = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(Position source, Position target) {
        if (source.isStraightDirectionTo(target) || source.isDiagonalDirectionTo(target)) {
            return;
        }
        throw new IllegalArgumentException("King은 대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Override
    protected void validateMoveCount(Position source, Position target) {
        int moveCount = source.calculateDistance(target);
        if (moveCount != MOVE_COUNT_LIMIT) {
            throw new IllegalArgumentException(
                    String.format("King은 한 번에 %d칸 이동할 수 있습니다.", MOVE_COUNT_LIMIT));
        }
    }

    @Override
    public Type type() {
        return Type.KING;
    }
}
