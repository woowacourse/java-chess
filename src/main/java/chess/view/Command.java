package chess.view;

import chess.domain.position.Position;

public class Command {

    private static final String EMPTY_REGEX = " ";
    private static final int BEFORE_POSITION = 1;
    private static final int AFTER_POSITION = 2;
    private static final int MENU_INDEX = 0;

    private final Menu menu;
    private Position beforePosition;
    private Position afterPosition;

    public Command(String value) {
        String[] stringArray = value.split(EMPTY_REGEX);
        menu = Menu.of(stringArray[MENU_INDEX]);

        if (menu.isMove()) {
            beforePosition = new Position(stringArray[BEFORE_POSITION]);
            afterPosition = new Position(stringArray[AFTER_POSITION]);
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Position getBeforePosition() {
        return beforePosition;
    }

    public Position getAfterPosition() {
        return afterPosition;
    }
}
