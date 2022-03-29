package chess.domain.piece;

import chess.domain.Team;
import chess.domain.board.Position;

public class Rook extends Piece {

    private static final String BLACK_SYMBOL = "R";
    private static final String WHITE_SYMBOL = "r";
    private static final int ROOK_SCORE = 5;

    public Rook(final Team team) {
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
        if (target.isDifferentRow(source) && target.isDifferentColumn(source)) {
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
        return ROOK_SCORE;
    }
}
