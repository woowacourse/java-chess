package chess.piece;

import chess.board.Position;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        if (from.equals(to)) {
            return false;
        }
        if (from.getRank() == to.getRank()) {
            return true;
        }
        if (from.getFile() == to.getFile()) {
            return true;
        }
        throw new IllegalArgumentException("Rook이 이동할 수 없는 경로입니다.");
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
