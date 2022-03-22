package chess.domain.piece;

public class EmptyPiece implements Piece {

    @Override
    public String getName() {
        return ".";
    }
}
