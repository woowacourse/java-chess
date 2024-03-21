package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public class EmptyPiece extends ChessPiece {

    public EmptyPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public EmptyPiece move(Position newPosition, boolean isDisturbed, boolean isSameTeamExist) {
        return this;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
