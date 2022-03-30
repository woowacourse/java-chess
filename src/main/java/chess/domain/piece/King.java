package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.move.StraightDirection;

import java.util.Map;

public class King extends Piece {

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public void move(Map<Point, Piece> ignored, Point from, Point to) {
        StraightDirection direction = StraightDirection.find(from, to);
        Point next = direction.nextOf(from);
        if (!next.equals(to)) {
            throw new IllegalArgumentException("[ERROR] 킹은 한 칸만 이동할 수 있습니다.");
        }
    }
}
