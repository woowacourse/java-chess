package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum RunningCommandType {

    MOVE(2),
    STATUS(0),
    END(0),
    ;

    private final int parameterSize;

    RunningCommandType(final int parameterSize) {
        this.parameterSize = parameterSize;
    }

    public static RunningCommandType from(final String command) {
        return Arrays.stream(values())
                .filter(startCommandType -> startCommandType.name()
                        .equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("게임 진행 중에 입력할 수 없는 명령어입니다."));
    }

    public void validateParameterSize(final List<String> parameters) {
        if (parameters.size() != parameterSize) {
            throw new IllegalArgumentException(
                    String.format("%s의 파라미터 수는 %d개 이어야 합니다.", this.name(), this.parameterSize)
            );
        }
    }
}
