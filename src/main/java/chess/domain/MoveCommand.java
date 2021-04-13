package chess.domain;

import java.util.Objects;

public class MoveCommand {

    private static final String MOVE_COMMAND_SIGNATURE = "move";
    private static final String BLANK = " ";

    private String source;
    private String target;

    public MoveCommand(String source, String target) {
        validateMoveCommand(source, target);
        this.source = source;
        this.target = target;
    }

    private void validateMoveCommand(String source, String target) {
        validateNull(source, target);
        validateEmpty(source, target);
    }

    private void validateNull(String source, String target) {
        Objects.requireNonNull(source, "source 값은 null 일 수 없습니다.");
        Objects.requireNonNull(target, "target 값은 null 일 수 없습니다.");
    }

    private void validateEmpty(String source, String target) {
        if (source.isEmpty()) {
            throw new IllegalArgumentException("source 는 빈값이 될 수 없습니다.");
        }
        if (target.isEmpty()) {
            throw new IllegalArgumentException("target 은 빈값이 될 수 없습니다.");
        }
    }

    public String source() {
        return source;
    }

    public String target() {
        return target;
    }

    public String moveCommand() {
        return MOVE_COMMAND_SIGNATURE + BLANK + source + BLANK + target;
    }
}
