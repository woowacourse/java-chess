package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Point;

public class Empty extends Piece {

    public Empty() {
        super(Color.NONE, PieceType.EMPTY);
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        return false;
    }
}
