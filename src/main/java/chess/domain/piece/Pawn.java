package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(Role.PAWN, team);
    }
}
