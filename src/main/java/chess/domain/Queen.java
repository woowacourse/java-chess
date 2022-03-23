package chess.domain;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team, Queen.class.getSimpleName());
    }
}