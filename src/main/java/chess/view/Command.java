package chess.view;

import java.util.Arrays;

public enum Command {

    LOGIN("login", "이미 로그인 되었습니다."),
    REGISTER("register", "이미 로그인 되었습니다."),
    START("start", ""),
    NEW("new", "게임 시작 전에는 새로운 게임 만들 수 없습니다."),
    EXIST("exist", "게임 시작 전에는 이어하기를 할 수 없습니다."),
    END("end", ""),
    MOVE("move", "게임 시작 전에는 기물을 이동할 수 없습니다."),
    STATUS("status", "게임 시작 전에는 승자를 확인할 수 없습니다.");

    private final String value;
    private final String initialErrorMessage;

    Command(
            final String value,
            final String initialErrorMessage
    ) {
        this.value = value;
        this.initialErrorMessage = initialErrorMessage;
    }

    public static Command from(final String command) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 커멘드입니다."));
    }

    public String getInitialErrorMessage() {
        return initialErrorMessage;
    }
}
