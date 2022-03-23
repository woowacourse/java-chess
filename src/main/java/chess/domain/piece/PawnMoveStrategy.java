package chess.domain.piece;

public class PawnMoveStrategy {

    public static MoveStrategy of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMoveStrategy();
        }
        if (color == Color.WHITE) {
            return new WhitePawnMoveStrategy();
        }
        throw new IllegalArgumentException("error");
    }
}
