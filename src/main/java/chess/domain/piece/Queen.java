package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Queen extends Division {
    public static final String EXIST_PIECE_BETWEEN_ERROR = "중간에 기물이 있어 이동할 수 없습니다.";
    private static final String QUEEN_DISPLAYNAME = "q";
    private static final int QUEEN_SCORE = 9;

    public Queen(final Color color, final Position position) {
        super(color, QUEEN_DISPLAYNAME, position);
    }

    @Override
    public void moveToEmpty(final Position to, final Pieces pieces) {
        if (position.isOrthogonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
            return;
        }
        if (position.isDiagonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(final Position to, final Pieces pieces) {
        final List<Position> positions = position.getBetween(to);
        if (positions.stream()
                .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException(EXIST_PIECE_BETWEEN_ERROR);
        }
    }

    @Override
    public void moveForKill(final Position to, final Pieces pieces) {
        this.moveToEmpty(to, pieces);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
