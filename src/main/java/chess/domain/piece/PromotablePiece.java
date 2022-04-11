package chess.domain.piece;

import chess.domain.board.position.Position;

public abstract class PromotablePiece extends Piece {

    PromotablePiece(final Team team) {
        super(team);
    }

    @Override
    public boolean canPromote(final Position sourcePosition) {
        return team.isPromotablePosition(sourcePosition);
    }

    public Piece promote(final String promotionType) {
        if (promotionType.equals(Queen.SYMBOL)) {
            return new Queen(team);
        }
        if (promotionType.equals(Rook.SYMBOL)) {
            return new Rook(team);
        }
        if (promotionType.equals(Bishop.SYMBOL)) {
            return new Bishop(team);
        }
        return new Knight(team);
    }
}
