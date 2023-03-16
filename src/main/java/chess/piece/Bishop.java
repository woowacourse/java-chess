package chess.piece;

import chess.board.Position;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {

        if (from.equals(to)) {
            throw new IllegalArgumentException("제자리로는 움직일 수 없습니다.");
        }
        if (this.team == toPiece.team) {
            throw new IllegalArgumentException("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
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
