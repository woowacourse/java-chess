package domain.piece.type.restricted;

import java.util.List;

import domain.piece.Camp;
import domain.piece.type.Type;

public class King extends RestrictedPiece {
    public static final int MOVE_DISTANCE_LIMIT = 1;

    public King(Camp camp, Type type) {
        super(camp, type);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        Integer distance = calculateDistance(gaps);
        if (distance != MOVE_DISTANCE_LIMIT) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
