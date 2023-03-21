package chess.controller.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private static final String DELIMITER = " ";
    private static final List<String> POSSIBLE_COMMANDS = List.of("start", "move", "end");
    private static final int NAME_INDEX = 0;

    private final String name;
    private final List<String> parameters;

    public Command(final String command) {
        validate(command);
        this.name = parseName(command);
        this.parameters = parseParameters(command);
    }

    private void validate(final String command) {
        List<String> parameters = List.of(command.split(DELIMITER));
        if (!POSSIBLE_COMMANDS.contains(parameters.get(NAME_INDEX))) {
            throw new IllegalArgumentException("지원하는 명령이 아닙니다.");
        }
    }

    private String parseName(final String command) {
        List<String> parameters = List.of(command.split(DELIMITER));
        return parameters.get(NAME_INDEX);
    }

    private List<String> parseParameters(final String command) {
        List<String> parameters = List.of(command.split(DELIMITER));
        return parameters.stream()
                .filter(parameter -> !POSSIBLE_COMMANDS.contains(parameter))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return new ArrayList<>(parameters);
    }

    @Override
    public String toString() {
        return name;
    }
}
