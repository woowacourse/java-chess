package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public class Bishop extends ChessPiece {

    public Bishop(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public Bishop move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
        if (isMoveInvalid(newPosition, isDisturbed, isSameTeamExist)) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Bishop(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    private boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();

        return !moveStrategy.canMove(currentPosition, newPosition) || isDisturbed || isSameTeamExist;
    }
}
