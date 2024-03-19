package piece;

import java.util.Objects;
import point.Position;

public class Blank extends Piece { // TODO piece 상위 클래스 만들기

    private Position position;

    @Override
    void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blank blank = (Blank) o;
        return Objects.equals(position, blank.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
