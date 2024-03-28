package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class King extends ChessPiece {

    private King(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public King(final PieceInfo pieceInfo) {
        this(pieceInfo, new KingMoveStrategy());
    }

    @Override
    public King move(final Position newPosition, final boolean isDisturbed,
                     final boolean isOtherPieceExist, final boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isSameTeamExist) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new King(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
