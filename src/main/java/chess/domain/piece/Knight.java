package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Position;

public class Knight extends JumperPiece {

    private static final int MANHATTAN_DISTANCE = 3;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    protected boolean canNotMovable(final Position from, final Position to, final Movement movement) {
        return calculateManhattanDistanceBetween(from, to) != MANHATTAN_DISTANCE;
    }

    private int calculateManhattanDistanceBetween(Position from, Position to) {
        final int rankDifference = Math.abs(to.calculateRankBetween(from));
        final int fileDifference = Math.abs(to.calculateFileBetween(from));

        return rankDifference + fileDifference;
    }
}
