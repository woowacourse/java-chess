package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.pawn.InitBlackPawnMoveRule;
import chess.domain.piece.moveRule.pawn.InitWhitePawnMoveRule;
import chess.domain.position.Position;

public class InitPawn extends AbstractPiece {
    private InitPawn(MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static InitPawn from(Color color) {
        if (color == Color.BLACK) {
            return new InitPawn(InitBlackPawnMoveRule.getInstance(), color);
        }
        return new InitPawn(InitWhitePawnMoveRule.getInstance(), color);
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
        return Pawn.from(color());
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
        return true;
    }
}
