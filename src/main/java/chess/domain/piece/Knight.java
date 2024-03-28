package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class Knight extends ChessPiece {

    private Knight(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Knight(final PieceInfo pieceInfo) {
        this(pieceInfo, new KnightMoveStrategy());
    }

    @Override
    public Knight move(final Position newPosition, final boolean isDisturbed,
                       final boolean isOtherPieceExist, final boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isSameTeamExist) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Knight(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
