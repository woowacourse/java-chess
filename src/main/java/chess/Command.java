package chess;

import java.util.function.Predicate;

public enum Command {

    START(command -> command.equals("start")),
    MOVE(command -> command.contains("move")),
    END(command -> command.equals("end")),
    STATUS(command -> command.equals("status"));

    private final Predicate<String> command;

    Command(Predicate<String> command) {
        this.command = command;
    }

    public boolean isValue(String input) {
        return this.command.test(input);
    }
}
