package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.gamestate.GamePieceExceptPawn;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;

public final class Knight extends GamePieceExceptPawn {

    private static final String INITIAL = "N";
    private static final double SCORE = 2.5;
    private static final int MOVE_RULE_FIRST = 1;
    private static final int MOVE_RULE_SECOND = 2;

    public Knight(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) == MOVE_RULE_SECOND
                && Math.abs(columnDifference) == MOVE_RULE_FIRST) {
            return true;
        }
        return Math.abs(rowDifference) == MOVE_RULE_FIRST
                && Math.abs(columnDifference) == MOVE_RULE_SECOND;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Collections.emptyList();
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
