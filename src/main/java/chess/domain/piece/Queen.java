package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

    private static final String BLACK_SYMBOL = "Q";
    private static final String WHITE_SYMBOL = "q";
    public static final int QUEEN_SCORE = 9;

    public Queen(final Team team) {
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
    public void checkReachable(final Position source, final Position target) {
        if (target.isDifferentDiagonal(source) && target.isDifferentRow(source) && target.isDifferentColumn(source)) {
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
        return QUEEN_SCORE;
    }
}
