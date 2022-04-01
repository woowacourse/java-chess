package chess.controller.menu;

import java.util.Arrays;
import java.util.function.Function;

public enum MenuType {

    START("start", text -> new Start()),
    MOVE("move", MenuType::toMove),
    STATUS("status", text -> new Status()),
    END("end", text -> new End());

    private static final int MENU_INDEX = 0;
    private static final int BEFORE_POSITION = 1;
    private static final int AFTER_POSITION = 2;
    private static final int SIZE = 3;

    private final String value;
    private final Function<String[], Menu> menu;

    MenuType(String value, Function<String[], Menu> menu) {
        this.value = value;
        this.menu = menu;
    }

    public static Menu of(String[] values) {
        return Arrays.stream(MenuType.values())
                .filter(it -> it.value.equalsIgnoreCase(values[MENU_INDEX]))
                .map(it -> it.menu.apply(values))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어 입니다."));
    }

    private static Move toMove(String[] value) {
        checkValidMoveRequest(value);
        return new Move(value[BEFORE_POSITION], value[AFTER_POSITION]);
    }

    private static void checkValidMoveRequest(String[] value) {
        if (value.length != SIZE) {
            throw new IllegalArgumentException("현재 위치와 이동 위치를 함께 입력해야합니다.");
        }
    }
}