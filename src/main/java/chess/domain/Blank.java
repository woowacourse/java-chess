package chess.domain;

public class Blank extends Piece {

    public Blank(Team team) {
        super(team, Blank.class.getSimpleName());
    }
}