package chess.domain.piece;

import chess.domain.Team;

public class Queen implements Piece{
    private final Team team;

    public Queen(Team team) {
        this.team = team;
    }

    @Override
    public void strategy() {

    }

    @Override
    public void canMove() {

    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "Q";
        }
        return "q";
    }
}
