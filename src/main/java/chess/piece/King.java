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

    private void validateDestination(final Piece toPiece) {
        if (this.team == toPiece.team) {
            throw new IllegalArgumentException("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
        }
    }
}
