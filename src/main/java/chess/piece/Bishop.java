package chess.piece;

import chess.board.Position;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        if (from.equals(to)) {
            return false;
        }
        int fileInterval = Math.abs(from.getFile().getIndex() - to.getFile().getIndex());
        int rankInterval = Math.abs(from.getRank().getIndex() - to.getRank().getIndex());

        if (fileInterval == rankInterval) {
            return true;
        }
        throw new IllegalArgumentException("Bishop이 이동할 수 없는 경로입니다.");
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
