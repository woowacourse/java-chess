package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Rook extends Division {
    private static final String ROOK_DISPLAYNAME = "r";
    private static final int ROOK_SCORE = 5;

    public Rook(final Color color, final Position position) {
        super(color, ROOK_DISPLAYNAME, position);
    }

    @Override
    public void moveToEmpty(final Position to, final Pieces pieces) {
        if (position.isOrthogonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(final Position to, final Pieces pieces) {
        final List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .filter(pieces::hasPieceOf)
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
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
        return ROOK_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
