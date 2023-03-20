package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {

        if (team == Team.WHITE) {
            return isWhitePawnMovable(from, to);
        }

        if (team == Team.BLACK) {
            return isBlackPawnMovable(from, to);
        }

        return false;
    }

    private boolean isWhitePawnMovable(final Position from, final Position to) {

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = getRankInterval(to.getRank(), from.getRank());

        if (isDiagonalOneStep(fileInterval, rankInterval)) {
            return true;
        }

        if (from.getRank() == Rank.TWO) {
            return isPawnInitMovable(from, to, rankInterval);
        }

        return isSameFile(fileInterval) && isMoveOneStep(rankInterval);
    }

    private boolean isBlackPawnMovable(final Position from, final Position to) {

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = getRankInterval(from.getRank(), to.getRank());

        if (isDiagonalOneStep(fileInterval, rankInterval)) {
            return true;
        }

        if (from.getRank() == Rank.SEVEN) {
            return isPawnInitMovable(from, to, rankInterval);
        }

        return isSameFile(fileInterval) && isMoveOneStep(rankInterval);
    }

    private boolean isPawnInitMovable(final Position from, final Position to, final int rankInterval) {
        return (rankInterval <= 2) && (from.getFile() == to.getFile());
    }

    private boolean isDiagonalOneStep(final int fileInterval, final int rankInterval) {
        return isMoveOneStep(fileInterval) && isMoveOneStep(rankInterval);
    }

    private boolean isSameFile(final int fileInterval) {
        return fileInterval == 0;
    }

    private boolean isMoveOneStep(final int rankInterval) {
        return rankInterval == 1;
    }

    private int getRankInterval(final Rank from, final Rank to) {
        return from.getIndex() - to.getIndex();
    }
}
