package piece;

import point.Point;

public class Blank extends Piece { // TODO piece 상위 클래스 만들기

    private Point point;

    @Override
    public String toString() {
        return ".";
    }
}
