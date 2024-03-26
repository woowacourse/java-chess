package view;

import controller.command.Command;
import controller.command.EndOnCommand;
import controller.command.MoveOnCommand;
import controller.command.StartOnCommand;
import controller.command.StatusOnCommand;
import domain.position.Position;

import java.util.Arrays;
import java.util.function.Function;

public enum CommandType {
    START(tokens -> isCommand(tokens, "START", 1),
            (tokens) -> new StartOnCommand()),
    END(tokens -> isCommand(tokens, "END", 1),
            (tokens) -> new EndOnCommand()),
    STATUS(tokens -> isCommand(tokens, "STATUS", 1),
            (tokens) -> new StatusOnCommand()),
    MOVE(tokens -> isCommand(tokens, "MOVE", 3),
            (tokens) -> new MoveOnCommand(new Position(tokens[1]), new Position(tokens[2])));

    private final Function<String[], Boolean> validator;
    private final Function<String[], Command> command;

    CommandType(final Function<String[], Boolean> validator, final Function<String[], Command> command) {
        this.validator = validator;
        this.command = command;
    }

    public static Command getCommand(String[] tokens) {
        CommandType findCommand = Arrays.stream(CommandType.values())
                .filter(command -> command.validator.apply(tokens))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return findCommand.command.apply(tokens);
    }

    private static boolean isCommand(String[] tokens, String commandName, int size) {
        return tokens.length == size && commandName.equals(tokens[0]);
    }

    public String message() {
        return name().toLowerCase();
    }
}
