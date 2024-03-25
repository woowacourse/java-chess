package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Set;

public class Knight extends Piece {

    private static final int DEFAULT_STEP_ONE = 1;
    private static final int DEFAULT_STEP_TWO = 2;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Movement movement) {
        return movement.getRankDistance() == DEFAULT_STEP_TWO && movement.getFileDistance() == DEFAULT_STEP_ONE
                || movement.getRankDistance() == DEFAULT_STEP_ONE && movement.getFileDistance() == DEFAULT_STEP_TWO;
    }

    @Override
    public Set<Position> getRoute(final Movement movement) {
        return Set.of();
    }
}
