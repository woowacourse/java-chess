package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class EmptyPiece extends ChessPiece {

    private EmptyPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public EmptyPiece(PieceInfo pieceInfo) {
        this(pieceInfo, new EmptyMoveStrategy());
    }

    @Override
    public EmptyPiece move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                           boolean isSameTeamExist) {
        return this;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
