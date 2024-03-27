package chess.domain.piece.type;

import chess.domain.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessRank;
import chess.domain.position.ChessDirection;
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
        ChessDirection chessDirection = movement.findDirection();
        if (color.isWhite()) {
            return chessDirection.isVertical() && chessDirection.isUpSide();
        }
        return chessDirection.isVertical() && chessDirection.isDownSide();
    }

    private boolean canAttack(final Movement movement) {
        ChessDirection chessDirection = movement.findDirection();
        if (color.isWhite()) {
            return chessDirection.isDiagonal() && chessDirection.isUpSide();
        }
        return chessDirection.isDiagonal() && chessDirection.isDownSide();
    }

    private boolean isMovableDistance(final Movement movement) {
        int distance = movement.calculateDistance();
        ChessDirection chessDirection = movement.findDirection();
        if (color.isWhite() && movement.isSourceRank(ChessRank.TWO) && chessDirection == ChessDirection.UP) {
            return (distance == 1 || distance == 2);
        }
        if (color.isBlack() && movement.isSourceRank(ChessRank.SEVEN) && chessDirection == ChessDirection.DOWN) {
            return (distance == 1 || distance == 2);
        }
        return distance == 1;
    }
}
