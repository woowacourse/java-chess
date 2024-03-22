package chess.domain.player;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Player {

    private final Team team;

    public Player(Team team) {
        this.team = team;
    }

    public boolean isMyPiece(Piece piece) {
        return piece.isSameTeam(team);
    }
}
