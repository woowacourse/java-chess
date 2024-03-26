package chess.domain.piece.type;

import chess.domain.position.Direction;
import chess.domain.position.Movement;
import chess.domain.piece.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessRank;

public final class Pawn extends Piece {

    public Pawn(final PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation) {
        return isMovableDirection(movement, pieceRelation) && isMovableDistance(movement);
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
            return movement.isCross() && type.contains(direction) && direction.isUpSide();
        }
        return movement.isCross() && type.contains(direction) && direction.isDownSide();
    }

    private boolean canAttack(final Movement movement) {
        Direction direction = movement.findDirection();
        if (color.isWhite()) {
            return movement.isDiagonal() && type.contains(direction) && direction.isUpSide();
        }
        return movement.isDiagonal() && type.contains(direction) && direction.isDownSide();
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
