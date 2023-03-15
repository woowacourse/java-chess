package chess.piece;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(null);
    }

    @Override
    public void move() {
        throw new IllegalArgumentException("빈 말은 움직일 수 없습니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
