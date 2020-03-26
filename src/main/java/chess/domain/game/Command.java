package chess.domain.game;

import java.util.Arrays;

public enum Command {
    START("start"),
    STATUS("status"),
    MOVE("move"),
    END("end");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean isExistCommand(String inputCommand) {
        return Arrays.stream(values())
                .anyMatch((o) -> (o.command.equals(inputCommand)));
    }

    public boolean equals(String command){
        return this.command.equals(command);
    }
}
