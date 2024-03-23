package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.*;

public class Pawn extends ChessPiece {

    private Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Pawn(PieceInfo pieceInfo) {
        this(pieceInfo, decidePawnMoveStrategy(pieceInfo));
    }

    private static MoveStrategy decidePawnMoveStrategy(PieceInfo pieceInfo) {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return new WhitePawnFirstMoveStrategy();
        }
        return new BlackPawnFirstMoveStrategy();
    }

    @Override
    public Pawn move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeam) {
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

    private boolean isUnableToMove(Position currentPosition, Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeam) {
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
