package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.RookMoveStrategy;

public class Rook extends ChessPiece {

    private Rook(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public Rook(PieceInfo pieceInfo) {
        this(pieceInfo, new RookMoveStrategy());
    }

    @Override
    public Rook move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isDisturbed || isSameTeamExist) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Rook(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
