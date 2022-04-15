package chess.model.state;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    NONE(""),
    ;

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command of(String command) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령을 입력해주세요."));
    }

    public boolean isStart() {
        return this.name.equals("start");
    }

    public boolean isEnd() {
        return this.name.equals("end");
    }

    public boolean isMove() {
        return this.name.equals("move");
    }

    public boolean isStatus() {
        return this.name.equals("status");
    }
}
