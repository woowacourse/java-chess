package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {

    private static final String BLACK_SYMBOL = "B";
    private static final String WHITE_SYMBOL = "b";

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    protected String createSymbol(final Team team) {
        if (team.isBlack()) {
            return BLACK_SYMBOL;
        }
        return WHITE_SYMBOL;
    }

    @Override
    public void validateMovement(final Position source, final Position target) {
        if (target.isDifferentDiagonal(source)) {
            throw new IllegalArgumentException(MOVEMENT_ERROR);
        }
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return 3;
    }
}
