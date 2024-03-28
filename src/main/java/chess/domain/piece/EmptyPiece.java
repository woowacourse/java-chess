package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class EmptyPiece extends ChessPiece {

    private EmptyPiece(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    public EmptyPiece(final PieceInfo pieceInfo) {
        this(pieceInfo, new EmptyMoveStrategy());
    }

    @Override
    public EmptyPiece move(final Position newPosition, final boolean isDisturbed,
                           final boolean isOtherPieceExist, final boolean isSameTeamExist) {
        return this;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
