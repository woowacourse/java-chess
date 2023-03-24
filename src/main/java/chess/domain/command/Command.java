package chess.domain.command;

import java.util.List;

public interface Command {
    void validateCommandsLength(List<String> commands);
}
