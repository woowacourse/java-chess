package chess.domain.pieces;

import chess.domain.moving.Moving;
import chess.domain.position.Position;

public abstract class NoKingPieces extends Piece {

    public NoKingPieces(final Position position, final Information information, final Moving moving) {
        super(position, information, moving);
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
