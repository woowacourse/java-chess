package chess.domain;

public class Bishop extends Piece {

    public Bishop(Team team, Position position) {
        super(team, Bishop.class.getSimpleName(), position);
    }
}
