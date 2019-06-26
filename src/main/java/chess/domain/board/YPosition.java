package chess.domain.board;

import java.util.List;
import java.util.Objects;

public class YPosition {
    private final String yPosition;
    private final int START_TYPE = 1;
    private final int END_TYPE = 8;

    public YPosition(String yPosition) {
        checkYPosition(yPosition);
        this.yPosition = yPosition;
    }

    private void checkYPosition(String yPosition) {
        if (START_TYPE > Integer.valueOf(yPosition) || Integer.valueOf(yPosition) > END_TYPE) {
            throw new IllegalArgumentException("범위를 벗어났습니다");
        }
    }

    public YPosition moveUp(int moveCnt) {
        return YPositionFactory.moveUp(yPosition, moveCnt);
    }

    public YPosition moveDown(int moveCnt) {
        return YPositionFactory.moveDown(yPosition, moveCnt);
    }

    public List<YPosition> moveUpToEnd() {
        return YPositionFactory.moveUpToEnd(yPosition);
    }

    public List<YPosition> moveDownToEnd() {
        return YPositionFactory.moveDownToEnd(yPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YPosition yPosition1 = (YPosition) o;

        return Objects.equals(yPosition, yPosition1.yPosition);
    }

    @Override
    public int hashCode() {
        return yPosition != null ? yPosition.hashCode() : 0;
    }

    @Override
    public String toString() {
        return yPosition;
    }
}
