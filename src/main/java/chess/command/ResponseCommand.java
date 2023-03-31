package chess.command;

import java.util.Arrays;

public enum ResponseCommand {
    YES("y"),
    NO("n");

    private final String response;

    ResponseCommand(final String response) {
        this.response = response;
    }

    public static boolean isYes(String input) {
        return findBy(input) == YES;
    }

    private static ResponseCommand findBy(String input) {
        return Arrays.stream(values())
                .filter(command -> input.equals(command.response))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n로 응답해주세요."));
    }
}
