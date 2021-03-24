package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.gamestate.GamePieceExceptPawn;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;

public final class King extends GamePieceExceptPawn {

    private static final String INITIAL = "K";
    private static final int SCORE = 0;

    public King(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == 1 || Math.abs(columnDifference) == 1;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Collections.emptyList();
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
