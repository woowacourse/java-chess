package chess.controller;

import java.util.Arrays;

public enum Commands {
    START("start"), END("end");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public static Commands of(String input){
        return Arrays.stream(values())
                .filter(commands -> commands.command.equals( input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다."));
    }
}
