package chess.piece;

import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Knight extends Piece {

    public Knight(final Team team) {
        super(team, PieceType.KNIGHT);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());
        if (!isMovablePosition(fileInterval, rankInterval)) {
            throw new IllegalArgumentException("Knight가 이동할 수 없는 경로입니다.");
        }
    }

    private boolean isMovablePosition(final int fileInterval, final int rankInterval) {
        final boolean isFileUnderTwo = fileInterval <= 2;
        final boolean isRankUnderTwo = rankInterval <= 2;
        return isFileUnderTwo && isRankUnderTwo && fileInterval + rankInterval == 3;
    }
}
