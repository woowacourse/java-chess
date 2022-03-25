package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(new Name("."), Color.NONE);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {

    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
