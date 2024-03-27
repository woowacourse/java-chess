package chess.domain.piece;

public class Empty extends NormalPiece {
    public Empty() {
        super(PieceType.EMPTY, Color.NONE);
    }
}
