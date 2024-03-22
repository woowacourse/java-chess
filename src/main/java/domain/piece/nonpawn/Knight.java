package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Knight extends NonPawn {
    private static final int MOVE_COUNT_LIMIT = 2;

    public Knight(Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(Position source, Position target) {
        if (!source.isKnightDirectionTo(target)) {
            throw new IllegalArgumentException("Knight는 L자 방향으로 이동해야 합니다.");
        }
    }

    @Override
    protected void validateMoveCount(Position source, Position target) {
        int moveCount = source.calculateDistance(target);
        if (moveCount != MOVE_COUNT_LIMIT) {
            throw new IllegalArgumentException(
                    String.format("Knight는 한 번에 %d칸 이동할 수 있습니다.", MOVE_COUNT_LIMIT));
        }
    }

    @Override
    public Type type() {
        return Type.KNIGHT;
    }
}
