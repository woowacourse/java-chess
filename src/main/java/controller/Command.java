package controller;

import java.util.Arrays;

public enum Command {

    START, END, MOVE, STATUS;

    public static Command from(final String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("안내된 명령어만 입력해주세요"));
    }

}
