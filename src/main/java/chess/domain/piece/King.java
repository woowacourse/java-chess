package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class King extends Piece {

    private static final String SYMBOL = "k";
    private static final double SCORE = 0;

    private static final BiPredicate<Integer, Integer> movingCondition =
            (rankMove, fileMove) -> Math.abs(rankMove) <= 1 && Math.abs(fileMove) <= 1;

    public King(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition);
        return new King(teamColor, targetPosition);
    }

    @Override
    public String getSymbol() {
        if (teamColor.isBlack()) {
            return SYMBOL.toUpperCase();
        }
        return SYMBOL;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean canPromote() {
        return false;
    }

    @Override
    public Piece promote(final String promotionType) {
        throw new IllegalStateException("King은 Promotion 할 수 없습니다.");
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
