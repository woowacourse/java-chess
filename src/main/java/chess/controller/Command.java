package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"), END("end"), MOVE("move"), STATUS("status");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String inputCommand){
        return Arrays.stream(values())
            .filter(value -> value.command.equals(inputCommand))
            .findFirst()
            .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 명령어입니다"));
    }
}
