package chess.domain.gamestate;

import java.util.Arrays;
import java.util.List;

public enum Option {
    STATUS("status"),
    MOVE("move"),
    START("start"),
    END("end");

    public static final int COMMAND_INDEX = 0;

    private final String option;

    Option(final String option) {
        this.option = option;
    }

    public static boolean hasNoOption(String input) {
        return Arrays.stream(values())
                .noneMatch(option -> option.option.equals(input));
    }

    public static boolean isStart(List<String> input) {
        return input.size() == 1 && START.option.equals(input.get(COMMAND_INDEX));
    }

    public static boolean isEnd(List<String> input) {
        return input.size() == 1 && END.option.equals(input.get(COMMAND_INDEX));
    }

    public static boolean isStatus(List<String> input) {
        return input.size() == 1 && STATUS.option.equals(input.get(COMMAND_INDEX));
    }

    public String getOption() {
        return option;
    }
}
