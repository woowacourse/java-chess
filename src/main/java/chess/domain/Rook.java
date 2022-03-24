package chess.domain;

public class Rook extends Piece {

    public Rook(Team team, Position position) {
        super(team, Rook.class.getSimpleName(), position);
    }
}
