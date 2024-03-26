package chess.piece;


import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class TestPiece extends Piece {

    public TestPiece(Color color) {
        super(color, PieceType.EMPTY);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }


    @Override
    public boolean canCatch(Position from, Position to) {
        return false;
    }
}
