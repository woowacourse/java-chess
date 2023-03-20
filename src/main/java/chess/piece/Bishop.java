package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());

        return fileInterval == rankInterval;
    }
}
