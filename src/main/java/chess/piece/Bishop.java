package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {
        validateStay(from, to);
        validateDestination(toPiece);

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());
        if (fileInterval == rankInterval) {
            return true;
        }
        throw new IllegalArgumentException("Bishop이 이동할 수 없는 경로입니다.");
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

    @Override
    public boolean isBishop() {
        return true;
    }
}
