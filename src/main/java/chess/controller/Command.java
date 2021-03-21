package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String signature;

    Command(String signature) {
        this.signature = signature;
    }

    public static Command findCommand(String commandInput) {
        return Arrays.stream(Command.values())
                .filter(command -> command.hasSameSignature(commandInput))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력했습니다."));
    }

    private boolean hasSameSignature(String value) {
        return this.signature.equals(value);
    }
}
