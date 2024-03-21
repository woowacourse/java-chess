package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public class Queen extends ChessPiece {

    public Queen(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public Queen move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
        if (isMoveInvalid(newPosition, isDisturbed, isSameTeamExist)) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Queen(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    private boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();

        return !moveStrategy.canMove(currentPosition, newPosition) || isDisturbed || isSameTeamExist;
    }
}
