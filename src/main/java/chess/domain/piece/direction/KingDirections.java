package chess.domain.piece.direction;

public class KingDirections extends Directions {

    public KingDirections() {
        super(false, Direction.aroundDirections(), Direction.aroundDirections());
    }
}
