package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public class King extends ChessPiece {

    public King(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                           boolean isSameTeamExist) {
        if (isMoveInvalid(newPosition, isDisturbed, isOtherPieceExist, isSameTeamExist)) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new King(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                 boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();

        return !moveStrategy.canMove(currentPosition, newPosition) || isSameTeamExist;
    }
}
