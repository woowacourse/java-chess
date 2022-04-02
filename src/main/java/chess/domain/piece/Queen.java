package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Queen extends Piece {

    private static final double SCORE = 9;
    private static final BiPredicate<Integer, Integer> movingCondition =
            Bishop.movingCondition.or(Rook.movingCondition);

    public Queen(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition);
        position.checkOtherPiecesInPathToTarget(targetPosition, convertToPositions(otherPieces));

        return new Queen(teamColor, targetPosition);
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
        throw new IllegalStateException("Queen은 Promotion 할 수 없습니다.");
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
