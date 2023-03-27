package view;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public enum BootingCommand {
    START("start"::equals),
    END("end"::equals),
    MOVE(command -> Pattern.matches(Constants.MOVE_COMMAND_REGEX, command));

    private final Predicate<String> condition;

    BootingCommand(Predicate<String> condition) {
        this.condition = condition;
    }

    public static BootingCommand findByCommand(String command) {
        return Arrays.stream(BootingCommand.values())
                .filter(bootingCommand -> bootingCommand.condition.test(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력입니다."));
    }

    public boolean isStart() {
        return this == START;
    }

    private static class Constants {
        private static final String MOVE_COMMAND_REGEX = "^move [a-z][0-9] [a-z][0-9]$";
    }
}
