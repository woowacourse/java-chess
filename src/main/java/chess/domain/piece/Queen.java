package chess.domain.piece;

import chess.domain.Team;
import chess.domain.board.Position;

import java.util.Locale;

public class Queen extends Piece {

    private static final String BLACK_SYMBOL = "Q";
    private static final String WHITE_SYMBOL = "q";
    private static final double QUEEN_SCORE = 9.0;
    private static final String NAME = "queen";

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
    public void checkReachable(final Piece targetPiece, final Position source, final Position target) {
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

    @Override
    public String getName() {
        return team.name().toLowerCase(Locale.ROOT) + "_" + NAME;
    }
}
