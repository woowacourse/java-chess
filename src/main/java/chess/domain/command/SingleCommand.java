package chess.domain.command;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public enum SingleCommand implements Command {
    START("start"),
    END("end"),
    STATUS("status");

    private final String prefix;

    SingleCommand(String prefix) {
        this.prefix = prefix;
    }

    public static Command from(List<String> input) {
        return Arrays.stream(SingleCommand.values())
                .filter(each -> each.prefix.equals(input.get(0)))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean isStart() {
        return this == START;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return this == STATUS;
    }

    @Override
    public Position getFromPosition() {
        throw new IllegalArgumentException();
    }

    @Override
    public Position getToPosition() {
        throw new IllegalArgumentException();
    }
}
