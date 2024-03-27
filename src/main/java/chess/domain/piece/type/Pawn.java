package chess.domain.piece.type;

import chess.domain.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessRank;
import chess.domain.position.Direction;
import chess.domain.position.Movement;

public final class Pawn extends Piece {

    public Pawn(final PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final boolean isOpened) {
        return isMovableDirection(movement, pieceRelation) && isMovableDistance(movement) && isOpened;
    }

    private boolean isMovableDirection(final Movement movement, final PieceRelation targetStatus) {
        if (targetStatus.isEnemy()) {
            return canAttack(movement);
        }
        return canMove(movement);
    }

    private boolean canMove(final Movement movement) {
        Direction direction = movement.findDirection();
        if (color.isWhite()) {
            return direction.isVertical() && direction.isUpSide();
        }
        return direction.isVertical() && direction.isDownSide();
    }

    private boolean canAttack(final Movement movement) {
        Direction direction = movement.findDirection();
        if (color.isWhite()) {
            return direction.isDiagonal() && direction.isUpSide();
        }
        return direction.isDiagonal() && direction.isDownSide();
    }

    private boolean isMovableDistance(final Movement movement) {
        int distance = movement.calculateDistance();
        Direction direction = movement.findDirection();
        if (color.isWhite() && movement.isSourceRank(ChessRank.TWO) && direction == Direction.UP) {
            return (distance == 1 || distance == 2);
        }
        if (color.isBlack() && movement.isSourceRank(ChessRank.SEVEN) && direction == Direction.DOWN) {
            return (distance == 1 || distance == 2);
        }
        return distance == 1;
    }
}
