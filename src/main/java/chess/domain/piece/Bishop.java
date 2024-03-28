package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class Bishop extends ChessPiece {

    private Bishop(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Bishop(final PieceInfo pieceInfo) {
        this(pieceInfo, new BishopMoveStrategy());
    }

    @Override
    public Bishop move(final Position newPosition, final boolean isDisturbed,
                       final boolean isOtherPieceExist, final boolean isSameTeamExist) {
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
