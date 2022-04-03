package chess.domain.piece;

import chess.domain.board.position.Position;

public abstract class PromotablePiece extends Piece {

    PromotablePiece(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public boolean canPromote() {
        return teamColor.isPromotablePosition(position);
    }

    public Piece promote(final String promotionType) {
        if (promotionType.equals(Queen.SYMBOL)) {
            return new Queen(teamColor, position);
        }
        if (promotionType.equals(Rook.SYMBOL)) {
            return new Rook(teamColor, position);
        }
        if (promotionType.equals(Bishop.SYMBOL)) {
            return new Bishop(teamColor, position);
        }
        return new Knight(teamColor, position);
    }
}
