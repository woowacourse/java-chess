package chess.view;

import chess.domain.position.Position;

public class MoveInfo {

    private static final int BEFORE_POSITION = 1;
    private static final int AFTER_POSITION = 2;
    private static final int SIZE = 3;

    private Position beforePosition;
    private Position afterPosition;

    public MoveInfo(String[] value) {
        validTypeMove(value);
        beforePosition = new Position(value[BEFORE_POSITION]);
        afterPosition = new Position(value[AFTER_POSITION]);
    }

    private void validTypeMove(String[] stringArray) {
        if (stringArray.length != SIZE) {
            throw new IllegalArgumentException("이동하고자 하는 위치와 도착 위치를 함께 입력해주세요.");
        }
    }

    public Position getBeforePosition() {
        return beforePosition;
    }

    public Position getAfterPosition() {
        return afterPosition;
    }
}
