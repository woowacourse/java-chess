package chess.view;

import chess.domain.position.Position;

public class Command {
    Menu menu;
    Position beforePosition;
    Position afterPosition;

    public Command(String value) {
        String[] stringArray = value.split(" ");
        menu = Menu.of(stringArray[0]);
        if (menu.isMove()) {
            beforePosition = new Position(stringArray[1]);
            afterPosition = new Position(stringArray[2]);
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
