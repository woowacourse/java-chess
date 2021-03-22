package chess.domain.piece.direction;

public class BishopDirections extends Directions {

    public BishopDirections() {
        super(true, Direction.diagonalDirections(), Direction.diagonalDirections());
    }
}
