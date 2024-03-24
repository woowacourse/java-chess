package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.WhitePawnNotFirstMoveStrategy;

public class Pawn extends ChessPiece {
    private static final WhitePawnNotFirstMoveStrategy whitePawnNotFirstMoveStrategy = new WhitePawnNotFirstMoveStrategy();
    private static final BlackPawnNotFirstMoveStrategy blackPawnNotFirstMoveStrategy = new BlackPawnNotFirstMoveStrategy();

    public Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece move(Position newPosition, boolean isObstacleInRange, boolean isOtherPieceExist,
                           boolean isSameTeam) {
        if (isMoveInvalid(newPosition, isObstacleInRange, isOtherPieceExist, isSameTeam)) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Pawn(newPieceInfo, changeMovedStrategy());
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                 boolean isSameTeam) {
        Position currentPosition = pieceInfo.getPosition();
        int diffX = Math.abs(currentPosition.getX() - newPosition.getX());

        boolean isInvalidVerticalMove = (diffX == 0) && isOtherPieceExist;
        boolean isInvalidDiagonalMove = (diffX == 1) && (!isOtherPieceExist || isSameTeam);

        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return true;
        }
        if (isDisturbed || isInvalidVerticalMove || isInvalidDiagonalMove) {
            return true;
        }
        return false;
    }

    private MoveStrategy changeMovedStrategy() {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return whitePawnNotFirstMoveStrategy;
        }
        return blackPawnNotFirstMoveStrategy;
    }
}
