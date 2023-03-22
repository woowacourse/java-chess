package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public final class King extends Piece {

    public King(final Team team) {
        super(team, PieceType.KING);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        final boolean isFileIntervalOne = File.calculateInterval(from.getFile(), to.getFile()) <= 1;
        final boolean isRankIntervalOne = Rank.calculateInterval(from.getRank(), to.getRank()) <= 1;

        return isFileIntervalOne && isRankIntervalOne;
    }
}
