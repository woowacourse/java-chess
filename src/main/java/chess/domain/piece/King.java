package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.StraightDirection;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public void move(Board ignored, Point from, Point to) {
        StraightDirection direction = StraightDirection.find(from, to);
        Point next = from.next(direction);
        if (!next.equals(to)) {
            throw new IllegalArgumentException("[ERROR] 킹은 한 칸만 이동할 수 있습니다.");
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
