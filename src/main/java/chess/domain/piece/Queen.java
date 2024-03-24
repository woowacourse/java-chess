package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;

public class Queen extends ChessPiece {

    private Queen(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Queen(PieceInfo pieceInfo) {
        this(pieceInfo, new QueenMoveStrategy());
    }

    @Override
    public Queen move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
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
