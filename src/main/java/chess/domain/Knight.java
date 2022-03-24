package chess.domain;

public class Knight extends Piece {

    public Knight(Team team, Position position) {
        super(team, Knight.class.getSimpleName(), position);
    }
}