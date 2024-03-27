package chess.view;

import chess.command.CommandType;
import java.util.Arrays;
import java.util.Objects;

public enum CommandTypeView {
    START("start", CommandType.START),
    END("end", CommandType.END),
    MOVE("move", CommandType.MOVE);

    private final String viewName;
    private final CommandType commandType;

    CommandTypeView(final String viewName, final CommandType commandType) {
        this.viewName = viewName;
        this.commandType = commandType;
    }

    public static CommandType find(String viewName) {
        return Arrays.stream(CommandTypeView.values())
                .filter(commandTypeView -> Objects.equals(commandTypeView.viewName, viewName))
                .findAny()
                .map(commandTypeView -> commandTypeView.commandType)
                .orElseThrow(() -> new IllegalArgumentException("입력 값은 준비된 CommandType이 아닙니다."));
    }
}
