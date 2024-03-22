package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class Knight extends ChessPiece {

    private Knight(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Knight(PieceInfo pieceInfo) {
        this(pieceInfo, new KnightMoveStrategy());
    }

    @Override
    public Knight move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
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
