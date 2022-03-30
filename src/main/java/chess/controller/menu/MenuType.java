package chess.controller.menu;

import chess.domain.board.Board;
import java.util.Arrays;

public enum MenuType {

    START("start", new Start()),
    MOVE("move", new Move()),
    STATUS("status", new Status()),
    END("end", new End());

    private final String value;
    private final Menu menu;

    MenuType(String value, Menu menu) {
        this.value = value;
        this.menu = menu;
    }

    public static MenuType of(String value) {
        return Arrays.stream(MenuType.values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어 입니다."));
    }

    public boolean play(Board board) {
        return this.menu.play(board);
    }

    public boolean isMove() {
        return this == MOVE;
    }
}