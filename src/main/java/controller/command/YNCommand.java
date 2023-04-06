package controller.command;

import java.util.Arrays;
import java.util.function.Predicate;

public enum YNCommand {
    YES("Y"::equals),
    NO("N"::equals);

    private final Predicate<String> condition;

    YNCommand(Predicate<String> condition) {
        this.condition = condition;
    }

    public static YNCommand from(String command) {
        return Arrays.stream(YNCommand.values())
                .filter(ynCommand -> ynCommand.condition.test(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효한 커맨드 입력이 아닙니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
