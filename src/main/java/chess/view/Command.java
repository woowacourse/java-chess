package chess.view;

import java.util.regex.Pattern;

public class Command {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String DELIMITER = " ";

    private static final Pattern MOVE_PARAMETERS_REGEX = Pattern.compile("^[a-h][1-8] [a-h][1-8]$");

    private final String prefix;
    private final String parameters;

    private Command(String prefix, String parameters) {
        validate(prefix, parameters);
        this.prefix = prefix;
        this.parameters = parameters;
    }

    public static Command from(String command) {
        String[] splitCommand = command.split(DELIMITER, 2);
        String prefix = splitCommand[0];
        if (splitCommand.length > 1) {
            return new Command(prefix, splitCommand[1]);
        }
        return new Command(prefix, "");
    }

    private void validate(String prefix, String parameters) {
        if (prefix.equals(START_COMMAND) || prefix.equals(END_COMMAND) || prefix.equals(MOVE_COMMAND)) {
            return;
        }
        if (MOVE_PARAMETERS_REGEX.matcher(parameters).matches()) {
            return;
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
    }

    public boolean isStart() {
        return prefix.equals(START_COMMAND);
    }

    public boolean isEnd() {
        return prefix.equals(END_COMMAND);
    }

    public String sourcePosition() {
        if (prefix.equals(MOVE_COMMAND)) {
            return parameters.split(DELIMITER)[0];
        }
        throw new IllegalStateException("move 명령어의 시작점을 찾을 수 없습니다.");
    }

    public String targetPosition() {
        if (prefix.equals(MOVE_COMMAND)) {
            return parameters.split(DELIMITER)[1];
        }
        throw new IllegalStateException("move 명령어의 도착점을 찾을 수 없습니다.");
    }
}
