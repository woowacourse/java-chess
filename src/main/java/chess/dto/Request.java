package chess.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Request {

    public static final String ARGUMENT_SEPARATOR = " ";
    private final String command;
    private final List<String> arguments;

    public Request(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static Request of(String input) {
        String[] split = input.split(ARGUMENT_SEPARATOR);
        List<String> arguments = toArguments(split);
        return new Request(split[0], arguments);
    }

    private static List<String> toArguments(String[] split) {
        return Arrays.stream(split)
                .skip(1)
                .collect(Collectors.toList());
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return List.copyOf(arguments);
    }
}
