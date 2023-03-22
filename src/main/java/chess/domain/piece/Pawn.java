package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.pawn.BlackPawnMoveRule;
import chess.domain.piece.moveRule.pawn.WhitePawnMoveRule;
import chess.domain.position.Position;

public class Pawn extends AbstractPiece {
    private Pawn(MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static Pawn from(Color color) {
        if (color == Color.BLACK) {
            return new Pawn(BlackPawnMoveRule.getInstance(), color);
        }
        return new Pawn(WhitePawnMoveRule.getInstance(), color);
    }

    @Override
    public Piece move(Position currentPosition, Position nextPosition, Piece pieceOfNextPosition) {
        validateSameColor(pieceOfNextPosition);
        validateMovement(currentPosition, nextPosition);
        if (currentPosition.isDiagonalEqual(nextPosition)) {
            validateDiagonalMoveToEmpty(pieceOfNextPosition);
        }
        if (currentPosition.isStraightEqual(nextPosition)) {
            validateStraightMoveToOpposite(pieceOfNextPosition);
        }
        return this;
    }

    private static void validateDiagonalMoveToEmpty(Piece pieceOfNextPosition) {
        if (pieceOfNextPosition.isEmpty()) {
            throw new IllegalArgumentException("폰은 빈 공간으로 대각선 이동할 수 없습니다.");
        }
    }

    private void validateStraightMoveToOpposite(Piece pieceOfNextPosition) {
        if (isOpponent(pieceOfNextPosition)) {
            throw new IllegalArgumentException("폰은 상대 기물이 있는 곳으로 직선 이동할 수 없습니다.");
        }
    }

    @Override
    public boolean isSliding() {
        return false;
    }
}
