package chess.domain.piece.direction;

public class QueenDirections extends PieceDirections {

    public QueenDirections() {
        super(true, Direction.aroundDirections(), Direction.aroundDirections());
    }
}
