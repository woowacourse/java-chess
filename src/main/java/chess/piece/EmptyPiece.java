package chess.piece;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Team.NONE, ".", null, 0);
    }
}
