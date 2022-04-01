package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class EmptyPiece extends Piece {
    public static final EmptyPiece EMPTY_PIECE = new EmptyPiece(Color.EMPTY);

    private EmptyPiece(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Position from, Position to, Board board) {
        return false;
    }
}
