package chess.view;

import java.util.Arrays;

public enum MenuOption {
    START("start"),
    END("end");

    private final String menuKeyword;

    MenuOption(String menuKeyword) {
        this.menuKeyword = menuKeyword;
    }

    public static MenuOption findByKeyword(String string) {
        return Arrays.stream(values())
                .filter(menu -> menu.menuKeyword.equalsIgnoreCase(string))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 선택지입니다."));
    }
}
