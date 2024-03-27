package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;
import chess.domain.strategy.*;

public class Pawn extends ChessPiece {

    private Pawn(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Pawn(final PieceInfo pieceInfo) {
        this(pieceInfo, decidePawnMoveStrategy(pieceInfo));
    }

    private static MoveStrategy decidePawnMoveStrategy(final PieceInfo pieceInfo) {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return new WhitePawnFirstMoveStrategy();
        }
        return new BlackPawnFirstMoveStrategy();
    }

    @Override
    public Pawn move(final Position newPosition, final boolean isDisturbed,
                     final boolean isOtherPieceExist, final boolean isSameTeam) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isUnableToMove(currentPosition, newPosition, isDisturbed, isOtherPieceExist, isSameTeam)) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Pawn(newPieceInfo, changeMovedStrategy());
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    private MoveStrategy changeMovedStrategy() {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return new WhitePawnNotFirstMoveStrategy();
        }
        return new BlackPawnNotFirstMoveStrategy();
    }

    private boolean isUnableToMove(final Position currentPosition, final Position newPosition,
                                   final boolean isDisturbed, final boolean isOtherPieceExist, final boolean isSameTeam) {
        int diffX = Math.abs(currentPosition.getXPosition() - newPosition.getXPosition());
        if (isDisturbed) {
            return true;
        }
        if (diffX == 0 && isOtherPieceExist) {
            return true;
        }
        return diffX == 1 && (!isOtherPieceExist || isSameTeam);
    }
}
