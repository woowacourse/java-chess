package chess.domain;

public class King extends Piece {

    public King(Team team) {
        super(team, King.class.getSimpleName());
    }
}