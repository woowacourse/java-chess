package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class King extends ChessPiece {

    private King(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public King(PieceInfo pieceInfo) {
        this(pieceInfo, new KingMoveStrategy());
    }

    @Override
    public King move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist) {
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
