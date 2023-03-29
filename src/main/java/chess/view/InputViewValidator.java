package chess.view;

import java.util.List;

public class InputViewValidator {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";

    public void validate(final String gameCommand) {
        List<String> commands = List.of(START, END, MOVE, STATUS);

        commands.stream()
            .filter(gameCommand::startsWith)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
