package chess.domain.game.command;

import java.util.List;

public interface ChessCommand {

    void execute(List<String> commands);
}
