package chess.domain.piece.direction;

public class BishopDirections extends PieceDirections {

    public BishopDirections() {
        super(true, Direction.diagonalDirections(), Direction.diagonalDirections());
    }
}
