package chess.piece;

import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Team.WHITE);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        final Piece toPiece = board.get(to);
        if (isImmovable(from, to, toPiece)) {
            throw new IllegalArgumentException("Pawn이 이동할 수 없는 경로입니다.");
        }
        checkBlockedVertical(from, to, board);
    }

    private boolean isImmovable(final Position from, final Position to, final Piece toPiece) {
        return !(isDiagonal(from, to) && toPiece.isBlack()) &&
                !(isFirstPosition(from) && isFirstMoveCondition(from, to)) &&
                !(isGeneral(from, to) && toPiece.isEmpty());
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
