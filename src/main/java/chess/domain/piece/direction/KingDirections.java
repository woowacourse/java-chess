package chess.domain.piece.direction;

public class KingDirections extends PieceDirections {

    public KingDirections() {
        super(false, Direction.aroundDirections(), Direction.aroundDirections());
    }
}
