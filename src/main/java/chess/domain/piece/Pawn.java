package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.WhitePawnNotFirstMoveStrategy;

public class Pawn extends ChessPiece {

    public Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeam) {
        if (isMoveInvalid(newPosition, isDisturbed, isOtherPieceExist, isSameTeam)) {
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

        boolean isInvalidVerticalMove = diffX == 0 && isOtherPieceExist;
        boolean isInvalidDiagonalMove = diffX == 1 && (!isOtherPieceExist || isSameTeam);

        return !moveStrategy.canMove(currentPosition, newPosition) || isDisturbed || isInvalidVerticalMove
                || isInvalidDiagonalMove;
    }

    private MoveStrategy changeMovedStrategy() {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return new WhitePawnNotFirstMoveStrategy();
        }
        return new BlackPawnNotFirstMoveStrategy();
    }
}
