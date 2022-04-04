package chess.domain.piece;

import chess.domain.Team;
import chess.domain.board.Position;

import java.util.Locale;

public class Rook extends Piece {

    private static final String BLACK_SYMBOL = "R";
    private static final String WHITE_SYMBOL = "r";
    private static final double ROOK_SCORE = 5;
    private static final String NAME = "rook";

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
    public void checkReachable(final Piece targetPiece, final Position source, final Position target) {
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

    @Override
    public String getName() {
        return team.name().toLowerCase(Locale.ROOT) + "_" + NAME;
    }
}
