package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.move.Moving;
import chess.domain.position.Position;

public abstract class NoKingPieces extends Piece {

    public NoKingPieces(final Position position, final String initial, final Team team, final Double score, final Moving moving) {
        super(position, initial, team, score, moving);
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }
}
