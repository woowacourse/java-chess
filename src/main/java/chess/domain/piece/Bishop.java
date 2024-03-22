package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class Bishop extends ChessPiece {

    private Bishop(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Bishop(PieceInfo pieceInfo) {
        this(pieceInfo, new BishopMoveStrategy());
    }

    @Override
    public Bishop move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isDisturbed || isSameTeamExist) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Bishop(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
