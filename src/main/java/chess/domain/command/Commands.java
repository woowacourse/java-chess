package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum Commands {
    START(0),
    END(0),
    MOVE(2),
    STATUS(0);

    private int optionCount;
    private List<String> options;

    Commands(int optionCount) {
        this.optionCount = optionCount;
        this.options = Arrays.asList(new String[optionCount]);
    }

    public static Commands from(String commandInput) {
        String[] c = commandInput.split("\\s+");
        Commands commands = Commands.valueOf(c[0].toUpperCase());
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
}
