package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.move.Movable;
import chess.domain.position.Position;

public abstract class NoKingPieces extends Piece {

    public NoKingPieces(final Position position, final String initial, final Team team, final Double score, final Movable movable) {
        super(position, initial, team, score, movable);
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
