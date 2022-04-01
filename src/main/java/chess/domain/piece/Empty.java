package chess.domain.piece;

import chess.domain.board.Point;

import java.util.Map;

public class Empty extends Piece {

    private static final Empty instance = new Empty();

    private Empty() {
        super(Color.NONE, PieceType.EMPTY);
    }

    public static Empty getInstance() {
        return instance;
    }

    @Override
    public void move(Map<Point, Piece> ignored, Point from, Point to) {
        throw new UnsupportedOperationException("[ERROR] 빈 칸은 이동할 수 없습니다.");
    }
}
