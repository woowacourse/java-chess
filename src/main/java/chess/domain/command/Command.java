package chess.domain.command;

import java.util.Arrays;

public enum Command {
    // TODO: enum을 class로 변경
    START("start"),
    END("end");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String command) {
        return Arrays.stream(values())
                .filter(ele -> ele.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", command)));
    }

    public boolean isStart() {
        return this.equals(START);
    }

    public boolean isNotEnd() {
        return !this.equals(END);
    }
}
