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

    public static Command matchByText(final String text) {
        return Arrays.stream(values())
                .filter(command -> command.text.equals(text))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 명령어 입니다."))
                .command;
    }
}
