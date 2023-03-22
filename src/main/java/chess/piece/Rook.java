package chess.piece;

import chess.board.Position;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {
        validateStay(from, to);
        validateDestination(toPiece);
        
        if (from.getRank() == to.getRank()) {
            return true;
        }
        if (from.getFile() == to.getFile()) {
            return true;
        }
        throw new IllegalArgumentException("Rook이 이동할 수 없는 경로입니다.");
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
    public boolean isRook() {
        return true;
    }
}
