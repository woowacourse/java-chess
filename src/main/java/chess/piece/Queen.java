package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team, PieceType.QUEEN);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {
        validateStay(from, to);
        validateDestination(toPiece);

        if (isMovablePosition(from, to)) {
            return true;
        }
        throw new IllegalArgumentException("Queen이 이동할 수 없는 경로입니다.");
    }

    private void validateStay(final Position from, final Position to) {
        if (from.isSamePosition(to)) {
            throw new IllegalArgumentException("제자리로는 움직일 수 없습니다.");
        }
    }

    private void validateDestination(final Piece toPiece) {
        if (this.team == toPiece.team) {
            throw new IllegalArgumentException("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
        }
    }

    private boolean isMovablePosition(final Position from, final Position to) {
        if (from.getRank() == to.getRank()) {
            return true;
        }
        if (from.getFile() == to.getFile()) {
            return true;
        }
        if (isSameInterval(from, to)) {
            return true;
        }
        return false;
    }

    private boolean isSameInterval(final Position from, final Position to) {
        return File.calculateInterval(from.getFile(), to.getFile()) ==
                Rank.calculateInterval(from.getRank(), to.getRank());
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
