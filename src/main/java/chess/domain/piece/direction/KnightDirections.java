package chess.domain.piece.direction;

public class KnightDirections extends Directions {

    public KnightDirections() {
        super(false, Direction.knightDirections(), Direction.knightDirections());
    }
}
