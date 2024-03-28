package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;

public class Queen extends ChessPiece {

    private Queen(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Queen(final PieceInfo pieceInfo) {
        this(pieceInfo, new QueenMoveStrategy());
    }

    @Override
    public Queen move(final Position newPosition, final boolean isDisturbed,
                      final boolean isOtherPieceExist, final boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isDisturbed || isSameTeamExist) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Queen(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
