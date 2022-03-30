package chess.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import chess.controller.Command;

public class Request {

    private static final String DELIMITER = " ";

    private final Command command;
    private final List<String> arguments;

    public Request(Command command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static Request of(String input) {
        validateNull(input);
        String[] split = input.split(DELIMITER);

        Command command = Command.find(split[0]);
        List<String> arguments = toArguments(split);
        return new Request(command, arguments);
    }

    private static void validateNull(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력이 잘못되었습니다.");
        }
    }

    private static List<String> toArguments(String[] split) {
        return Arrays.stream(split)
            .skip(1)
            .collect(Collectors.toList());
    }

    public Command getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return List.copyOf(arguments);
    }
}
