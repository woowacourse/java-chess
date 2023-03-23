package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Team.BLACK);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece toPiece) {
        validateStay(from, to);

        if (isDiagonal(from, to) && toPiece.isWhite()) {
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
        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = from.getRank().getIndex() - to.getRank().getIndex();

        return rankInterval == 1 && fileInterval == 1;
    }

    private boolean isFirstPosition(final Position from) {
        return from.getRank() == Rank.SEVEN;
    }

    private boolean isFirstMoveCondition(final Position from, final Position to) {
        return from.getRank().getIndex() - to.getRank().getIndex() <= 2 && from.getFile() == to.getFile();
    }

    private boolean isGeneral(final Position from, final Position to) {
        final int rankInterval = from.getRank().getIndex() - to.getRank().getIndex();
        return rankInterval == 1;
    }
}
