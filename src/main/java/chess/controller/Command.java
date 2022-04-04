package chess.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start", new ArrayList<>()),
    FINISH("end", new ArrayList<>()),
    MOVE("move", List.of("source", "destination")),
    STATUS("status", new ArrayList<>());

    private final String input;

    private final List<String> parameters;

    Command(String input, List<String> parameters) {
        this.input = input;
        this.parameters = parameters;
    }

    public static Command find(String input) {
        return Arrays.stream(values())
            .filter(value -> value.input.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 명령어가 없습니다."));
    }

    public List<String> getParameters() {
        return parameters;
    }
}
