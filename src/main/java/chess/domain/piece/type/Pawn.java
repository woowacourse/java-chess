package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Set;

public class Pawn extends Piece {

    public static final int DEFAULT_STEP = 1;
    private static final int INIT_AVAILABLE_STEP = 2;
    private static final Rank INIT_WHITE_RANK = Rank.TWO;
    private static final Rank INIT_BLACK_RANK = Rank.SEVEN;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Movement movement) {
        if (this.isBlack()) {
            return canBlackMove(movement);
        }

        return canWhiteMove(movement);
    }

    @Override
    public Set<Position> getCatchRoute(final Movement movement) {
        if (!(movement.isDiagonal() && movement.getRankDistance() == Pawn.DEFAULT_STEP)) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선으로만 기물을 잡을 수 있습니다.");
        }
        return getRoute(movement);
    }

    private boolean canBlackMove(final Movement movement) {
        if (isInitPosition(movement)) {
            return !movement.isUp() && movement.getRankDistance() == INIT_AVAILABLE_STEP
                    || !movement.isUp() && movement.getRankDistance() == DEFAULT_STEP;
        }

        return movement.isVertical() && !movement.isUp() && movement.getRankDistance() == DEFAULT_STEP;
    }

    private boolean canWhiteMove(final Movement movement) {
        if (isInitPosition(movement)) {
            return movement.isVertical() && movement.getRankDistance() == INIT_AVAILABLE_STEP
                    || movement.isVertical() && movement.getRankDistance() == DEFAULT_STEP;
        }

        return movement.isUp() && movement.getRankDistance() == DEFAULT_STEP;
    }

    private boolean isInitPosition(final Movement movement) {
        if (this.isBlack()) {
            return movement.isCurrentRank(INIT_BLACK_RANK);
        }

        return movement.isCurrentRank(INIT_WHITE_RANK);
    }
}
