package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class Rook extends Piece {

    public Rook(Team team) {
        super(Role.ROOK, team);
    }
}
