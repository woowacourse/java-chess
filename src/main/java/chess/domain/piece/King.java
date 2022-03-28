package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;
import java.util.Optional;

public class King extends Piece {

    private static final String BLACK_SYMBOL = "K";
    private static final String WHITE_SYMBOL = "k";
    public static final int KING_SCORE = 0;

    public King(final Team team) {
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
        List<Direction> directions = Direction.getKingDirection();
        if (!canMove(source, target, directions)) {
            throw new IllegalArgumentException(MOVEMENT_ERROR);
        }
    }

    private boolean canMove(final Position source, final Position target, final List<Direction> directions) {
        return directions.stream()
                .map(source::addDirection)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(position -> position.equals(target));
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
        return true;
    }

    @Override
    public double getScore() {
        return KING_SCORE;
    }
}
