package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public class Blank extends Piece {

    public Blank(Color color) {
        super(Type.BLANK, color);
    }

    @Override
    public List<Point> action(Point source, Point target, boolean hasEnemy) {
        throw new IllegalArgumentException("빈 칸은 공격할 수 없습니다!");
    }
}
