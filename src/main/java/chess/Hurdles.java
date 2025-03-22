package chess;

import java.util.List;

public class Hurdles {

    //TODO 가능하면 교체
    private final List<Position> positions;

    public Hurdles(List<Position> positions) {
        this.positions = positions;
    }

    public void checkCrash(Position position) {
        if (positions.contains(position)) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
    }
}
