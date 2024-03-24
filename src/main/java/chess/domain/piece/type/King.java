package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    private static final int DEFAULT_STEP = 1;

    public King(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> getRoute(final Movement movement) {
        if ((movement.isVertical() && movement.getRankDistance() == DEFAULT_STEP)
                || (movement.isHorizontal() && movement.getFileDistance() == DEFAULT_STEP)
                || (movement.isDiagonal() && movement.getRankDistance() == DEFAULT_STEP)) {
            return new HashSet<>();
        }

        throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }
}
