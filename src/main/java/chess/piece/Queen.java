package chess.piece;

import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team, PieceType.QUEEN);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        if (isDiagonal(from, to)) {
            validateDiagonal(from, to, board);
            return;
        }
        if (isStraight(from, to)) {
            validateStraight(from, to, board);
            return;
        }
        throw new IllegalArgumentException("Queen이 이동할 수 없는 경로입니다.");
    }


    private boolean isDiagonal(final Position from, final Position to) {
        return isSameInterval(from, to);
    }

    private boolean isSameInterval(final Position from, final Position to) {
        return File.calculateInterval(from.getFile(), to.getFile()) ==
                Rank.calculateInterval(from.getRank(), to.getRank());
    }

    private boolean isStraight(final Position from, final Position to) {
        if (from.getRank() == to.getRank()) {
            return true;
        }
        if (from.getFile() == to.getFile()) {
            return true;
        }
        return false;
    }
}
