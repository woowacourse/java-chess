package chess.domain.piece.direction;

public class KnightDirections extends PieceDirections {

    public KnightDirections() {
        super(false, Direction.knightDirections(), Direction.knightDirections());
    }
}
