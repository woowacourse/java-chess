package chess.view;

import java.util.Arrays;

public enum InputOption {

    START("start"),
    END("end");

    private static final String NOT_EXIST_OPTION = "[ERROR] start나 end만 입력할 수 있습니다.";

    private final String option;

    InputOption(final String option) {
        this.option = option;
    }

    public static InputOption from(String input) {
        return Arrays.stream(InputOption.values())
            .filter(inputOption -> inputOption.option.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_OPTION));
    }
}
