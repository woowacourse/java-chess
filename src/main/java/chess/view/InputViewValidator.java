package chess.view;

import java.util.List;

public class InputViewValidator {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    public void validate(final String gameCommand) {
        List<String> commands = List.of(START, END, MOVE);

        commands.stream()
                .filter(command -> gameCommand.startsWith(command))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
