package chess.piece;


import chess.position.Position;

public class TestPiece extends Piece {

    public TestPiece(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        return false;
    }
}
