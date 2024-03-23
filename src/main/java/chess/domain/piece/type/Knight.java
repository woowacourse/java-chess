package chess.domain.piece.type;

import chess.domain.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    private static final int DEFAULT_STEP_ONE = 1;
    private static final int DEFAULT_STEP_TWO = 2;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> getRoute(final Movement movement) {
        if (movement.getRankDistance() == DEFAULT_STEP_TWO && movement.getFileDistance() == DEFAULT_STEP_ONE
                || movement.getRankDistance() == DEFAULT_STEP_ONE && movement.getFileDistance() == DEFAULT_STEP_TWO) {
            return new HashSet<>();
        }

        throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }
}
