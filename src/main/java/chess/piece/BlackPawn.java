package chess.piece;

import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Team.BLACK);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        final Piece toPiece = board.get(to);
        if (!(isDiagonal(from, to) && toPiece.isWhite()) &&
                !(isFirstPosition(from) && isFirstMoveCondition(from, to)) &&
                !(isGeneral(from, to) && toPiece.isEmpty())) {
            throw new IllegalArgumentException("Pawn이 이동할 수 없는 경로입니다.");
        }
        final Rank fromRank = from.getRank();
        final Rank toRank = to.getRank();
        final int min = Math.min(fromRank.getIndex(), toRank.getIndex()) + 1;
        final int max = Math.max(fromRank.getIndex(), toRank.getIndex()) - 1;

        for (int i = min; i <= max; i++) {
            final Piece validationPiece = board.get(new Position(from.getFile(), Rank.from(i)));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateBlockedRoute(final Piece validationPiece) {
        if (!validationPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }
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
