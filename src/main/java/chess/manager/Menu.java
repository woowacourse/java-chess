package chess.manager;

import java.util.Arrays;

public enum Menu {
    START("start"),
    END("end");

    private final String command;

    Menu(final String command) {
        this.command = command;
    }

    public static Menu of(final String input){
        return Arrays.stream(values())
                .filter(menu -> menu.command.equals(input))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isEnd(final String input){
        return of(input).equals(END);
    }
}
