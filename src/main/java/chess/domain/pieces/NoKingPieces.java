package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class NoKingPieces extends Piece{

    public NoKingPieces(Position position, String initial, Team team) {
        super(position, initial, team);
    }

    @Override
    public final boolean isKing() {
        return false;
    }
}
