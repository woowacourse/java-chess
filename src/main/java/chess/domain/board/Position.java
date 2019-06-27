package chess.domain.board;

import java.util.List;
import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class Position {
    private final int position;
    private final int START = 1;
    private final int END = 8;

    public Position(int position) {
        checkPosition(position);
        this.position = position;
    }

    private void checkPosition(int position) {
        if (START >position || position > END) {
            throw new IllegalArgumentException("범위를 벗어났습니다");
        }
    }

    public Position moveGo(int moveCnt) {
        return PositionFactory.moveGo(position, moveCnt);
    }

    public Position moveBack(int moveCnt) {
        return PositionFactory.moveBack(position, moveCnt);
    }

    public List<Position> moveGoToEnd() {
        return PositionFactory.moveGoToEnd(position);
    }

    public List<Position> moveBackToEnd() {
        return PositionFactory.moveBackToEnd(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position &&
                START == position1.START &&
                END == position1.END;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, START, END);
    }

    @Override
    public String toString() {
        return String.valueOf(position);
    }
}
