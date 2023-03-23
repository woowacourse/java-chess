package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Knight extends Piece {

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {
        validateStay(from, to);
        validateDestination(toPiece);

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());
        if (isMovablePosition(fileInterval, rankInterval)) {
            return true;
        }
        throw new IllegalArgumentException("Knight가 이동할 수 없는 경로입니다.");
    }

    private void validateStay(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("제자리로는 움직일 수 없습니다.");
        }
    }

    private void validateDestination(final Piece toPiece) {
        if (this.team == toPiece.team) {
            throw new IllegalArgumentException("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
        }
    }

    private boolean isMovablePosition(final int fileInterval, final int rankInterval) {
        final boolean isFileUnderTwo = fileInterval <= 2;
        final boolean isRankUnderTwo = rankInterval <= 2;
        return isFileUnderTwo && isRankUnderTwo && fileInterval + rankInterval == 3;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
