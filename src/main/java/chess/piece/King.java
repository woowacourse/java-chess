package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class King extends Piece {

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
