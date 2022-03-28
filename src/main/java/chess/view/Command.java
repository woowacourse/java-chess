package chess.view;

import chess.domain.position.Position;
import chess.menu.Type;

public class Command {

    private static final String EMPTY_REGEX = " ";
    private static final int BEFORE_POSITION = 1;
    private static final int AFTER_POSITION = 2;
    private static final int MENU_INDEX = 0;

    private final Type type;
    private Position beforePosition;
    private Position afterPosition;

    public Command(String value) {
        String[] stringArray = value.split(EMPTY_REGEX);
        type = Type.of(stringArray[MENU_INDEX]);

        if (type.isMove()) {
            validTypeMove(stringArray);
            beforePosition = new Position(stringArray[BEFORE_POSITION]);
            afterPosition = new Position(stringArray[AFTER_POSITION]);
        }
    }

    private void validTypeMove(String[] stringArray) {
        if(stringArray.length == BEFORE_POSITION){
            throw new IllegalArgumentException("이동하고자 하는 위치와 도착 위치를 함께 입력해주세요.");
        }
    }

    public Type getType() {
        return type;
    }

    public Position getBeforePosition() {
        return beforePosition;
    }

    public Position getAfterPosition() {
        return afterPosition;
    }
}
