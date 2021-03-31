package chess.domain.game;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START(0),
    END(0),
    MOVE(2),
    STATUS(0);

    public static final String SPACE_REGEX = "\\s+";
    private int optionCount;
    private List<String> options;

    Command(int optionCount) {
        this.optionCount = optionCount;
        this.options = Arrays.asList(new String[optionCount]);
    }

    public static Command from(String input) {
        String[] argv = input.split(SPACE_REGEX);
        Command command = Command.valueOf(argv[0].toUpperCase());
        command.setOptions(argv);
        return command;
    }

    private void setOptions(String[] argv) {
        validateOptionSize(argv);
        for (int i = 0; i < optionCount; i++) {
            this.options.set(i, argv[i + 1]);
        }
    }

    private void validateOptionSize(String[] argv) {
        if (argv.length != optionCount + 1) {
            throw new IllegalArgumentException("잘못된 갯수의 옵션 입력입니다.");
        }
    }

    public List<String> options() {
        return options;
    }
}
