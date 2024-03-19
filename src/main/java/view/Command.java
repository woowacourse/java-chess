package view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end");

    private final String text;

    Command(String text) {
        this.text = text;
    }

    public static Command findByText(final String text) {
        return Arrays.stream(values())
                .filter(command -> command.text.equals(text))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 명령어 입니다."));
    }
}
