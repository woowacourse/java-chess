package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.gamestate.GamePieceExceptPawn;
import chess.domain.position.Position;
import java.util.List;

public final class Rook extends GamePieceExceptPawn {

    private static final String INITIAL = "R";
    private static final int SCORE = 5;

    public Rook(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return isStraight(rowDifference, columnDifference);
    }

    private boolean isStraight(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.route(from, to);
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
