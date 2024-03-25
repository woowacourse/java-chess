package chess.view.matcher;

import chess.domain.Command;

import java.util.Arrays;

public enum CommandMatcher {
    START("start", Command.START),
    END("end", Command.END),
    MOVE("move", Command.MOVE);

    private final String text;
    private final Command command;

    CommandMatcher(String text, Command command) {
        this.text = text;
        this.command = command;
    }

    public static Command matchByText(final String inputText) {
        return Arrays.stream(values())
                .filter(command -> command.text.equals(inputText))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 명령어입니다."))
                .command;
    }

    public static boolean isPresentCommand(final Command command) {
        return Arrays.stream(values())
                .anyMatch(commandMatcher -> commandMatcher.command == command);
    }
}
