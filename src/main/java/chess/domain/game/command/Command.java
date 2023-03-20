package chess.domain.game.command;

import java.util.List;

public interface Command {

    boolean isMatch(List<String> commands);

    void execute(List<String> commands);
}
