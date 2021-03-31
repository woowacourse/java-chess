package chess.domain.game;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START(0),
    END(0),
    MOVE(2),
    STATUS(0);

    private int optionCount;
    private List<String> options;

    Command(int optionCount) {
        this.optionCount = optionCount;
        this.options = Arrays.asList(new String[optionCount]);
    }

    public static Command from(String commandInput) {
        String[] c = commandInput.split("\\s+");
        Command commands = Command.valueOf(c[0].toUpperCase());
        commands.setOptions(c);
        return commands;
    }

    public void setOptions(String... commandsInput) {
        validateOptionSize(commandsInput);
        for (int i = 0; i < optionCount; i++) {
            this.options.set(i, commandsInput[i+1]);
        }
    }

    private void validateOptionSize(String... commandsInput) {
        if (commandsInput.length != optionCount + 1) {
            throw new IllegalArgumentException("잘못된 갯수의 옵션 입력입니다.");
        }
    }

    public List<String> getOptions() {
        return options;
    }
}
