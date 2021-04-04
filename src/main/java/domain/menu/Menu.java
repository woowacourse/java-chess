package domain.menu;

import domain.ChessGame;
import dto.MenuDto;
import domain.exception.InvalidMenuException;

import java.util.Arrays;

public enum Menu {
    START("start", new Start()),
    STATUS("status", new Status()),
    MOVE("move", new Move()),
    END("end", new End());

    private String button;
    private Command strategy;

    Menu(String button, Command strategy) {
        this.button = button;
        this.strategy = strategy;
    }

    public static Menu findMenu(String input) {
        return Arrays.stream(values())
                .filter(menu -> menu.button.equals(input))
                .findAny()
                .orElseThrow(() -> new InvalidMenuException());
    }

    public MenuDto execute(String command, ChessGame game) {
        return strategy.execute(command, game);
    }

    public boolean isEnd() {
        return this == END;
    }
}
