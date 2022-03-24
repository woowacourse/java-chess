package chess.domain;

public class Queen extends Piece {

    public Queen(Team team, Position position) {
        super(team, Queen.class.getSimpleName(), position);
    }
}