package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Team.WHITE);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {
        validateStay(from, to);

        if (isDiagonal(from, to) && toPiece.isBlack()) {
            return true;
        }
        if (isFirstPosition(from) && isFirstMoveCondition(from, to)) {
            return true;
        }
        if (isGeneral(from, to) && toPiece.isEmpty()) {
            return true;
        }
        throw new IllegalArgumentException("Pawn이 이동할 수 없는 경로입니다.");
    }

    private boolean isDiagonal(final Position from, final Position to) {
        final int rankInterval = to.getRank().getIndex() - from.getRank().getIndex();
        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());

        return rankInterval == 1 && fileInterval == 1;
    }

    private boolean isFirstPosition(final Position from) {
        return from.getRank() == Rank.TWO;
    }

    private boolean isFirstMoveCondition(final Position from, final Position to) {
        return to.getRank().getIndex() - from.getRank().getIndex() <= 2 && to.getFile() == from.getFile();
    }

    private boolean isGeneral(final Position from, final Position to) {
        final int rankInterval = to.getRank().getIndex() - from.getRank().getIndex();
        return rankInterval == 1;
    }
}
