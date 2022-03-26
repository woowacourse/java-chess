package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color, PieceType.EMPTY);
    }

    @Override
    public void move(Board board, Point from, Point to) {
        throw new UnsupportedOperationException("[ERROR] 빈 칸은 이동할 수 없습니다.");
    }
}
