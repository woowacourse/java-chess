package chess.piece;

import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class King extends Piece {

    public King(final Team team) {
        super(team, PieceType.KING);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        final boolean isFileIntervalOne = File.calculateInterval(from.getFile(), to.getFile()) <= 1;
        final boolean isRankIntervalOne = Rank.calculateInterval(from.getRank(), to.getRank()) <= 1;
        if (!(isFileIntervalOne && isRankIntervalOne)) {
            throw new IllegalArgumentException("King이 이동할 수 없는 경로입니다.");
        }
    }
}
