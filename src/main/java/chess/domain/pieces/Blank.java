package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public class Blank extends Piece {

    public Blank(Color color) {
        super(color);
    }

    @Override
    public List<Point> move(Point source, Point target) throws IllegalArgumentException {
       throw new IllegalArgumentException("빈 칸은 이동할 수 없습니다!");
    }

    @Override
    public List<Point> attack(Point source, Point target) throws IllegalArgumentException {
        throw new IllegalArgumentException("빈 칸은 공격할 수 없습니다!");
    }
}
