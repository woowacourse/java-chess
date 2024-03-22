package chess.domain.piece;


import chess.domain.position.Position;

public class TestPiece extends Piece {

    public TestPiece(Color color) {
        super(color, ((fileDifference, rankDifference) -> false));
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        return false;
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return false;
    }
}
