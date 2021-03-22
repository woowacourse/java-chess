package chess.domain.piece.direction;

public class QueenDirections extends Directions {

    public QueenDirections() {
        super(true, Direction.aroundDirections(), Direction.aroundDirections());
    }
}
