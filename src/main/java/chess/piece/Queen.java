package chess.piece;

import chess.board.Position;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece piece) {
        if (from.equals(to)) {
            return false;
        }
        if (from.getRank() == to.getRank()) {
            return true;
        }
        if (from.getFile() == to.getFile()) {
            return true;
        }
        int fileInterval = Math.abs(from.getFile().getIndex() - to.getFile().getIndex());
        int rankInterval = Math.abs(from.getRank().getIndex() - to.getRank().getIndex());

        if (fileInterval == rankInterval) {
            return true;
        }
        throw new IllegalArgumentException("Queen이 이동할 수 없는 경로입니다.");
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
