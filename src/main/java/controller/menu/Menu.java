package controller.menu;

import domain.ChessGame;
import domain.exception.InvalidMenuException;

import java.util.Arrays;

public enum Menu {
    START("start"  , new Start()),
    STATUS("status", new Status()),
    MOVE("move", new Move()),
    END("end", new End());

    private String command;
    private Command strategy;

    Menu(String command, Command strategy) {
        this.command = command;
        this.strategy = strategy;
    }

    public static Menu findMenu(String input) {
        return Arrays.stream(values())
                .filter(menu -> menu.command.equals(input))
                .findAny()
                .orElseThrow(() -> new InvalidMenuException());
    }

    public void execute(String command, ChessGame game) {
        strategy.execute(command, game);
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
