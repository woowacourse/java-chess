package chess.domain;

public class King extends Piece {

    public King(Team team, Position position) {
        super(team, King.class.getSimpleName(), position);
    }
}