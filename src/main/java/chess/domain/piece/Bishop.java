package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Bishop extends Piece {

    static final String SYMBOL = "b";
    private static final double SCORE = 3;

    static final BiPredicate<Integer, Integer> movingCondition =
            (rankMove, fileMove) -> Math.abs(rankMove) == Math.abs(fileMove);

    public Bishop(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition);
        position.checkOtherPiecesInPathToTarget(targetPosition, convertToPositions(otherPieces));

        return new Bishop(teamColor, targetPosition);
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
        return false;
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
        throw new IllegalStateException("Bishop은 Promotion 할 수 없습니다.");
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
