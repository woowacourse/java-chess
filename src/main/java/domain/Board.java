package domain;

import domain.piece.*;
import domain.point.Point;
import domain.util.BoardInitializer;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Piece>> pieceStatus;

    public Board(List<List<Piece>> pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    public static Board initialize() {
        return new Board(BoardInitializer.initializeBoard());
    }

    public List<List<Piece>> findCurrentStatus() {
        return new ArrayList<>(pieceStatus);
    }

    public void move(String from, String to) {
        Point fromPoint = Point.fromSymbol(from);
        Point toPoint = Point.fromSymbol(to);

        move(fromPoint, toPoint);
    }

    private void move(Point fromPoint, Point toPoint) {
        Piece piece = pieceStatus.get(fromPoint.findIndexFromBottom())
                .get(fromPoint.findIndexFromLeft());

        if (piece.isEmpty()) {
            throw new IllegalArgumentException("입력한 위치에는 이동 가능한 기물이 존재하지 않습니다.");
        }

        pieceStatus.get(fromPoint.findIndexFromBottom())
                .set(fromPoint.findIndexFromLeft(), new Empty());
        pieceStatus.get(toPoint.findIndexFromBottom())
                .set(toPoint.findIndexFromLeft(), piece);
    }
}
