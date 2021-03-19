package chess.domain.gamestate;

import chess.domain.command.Command;
import java.util.List;

public interface GameState {

    GameState operateCommand(Command command, List<String> arguments);

    boolean isRunning();
}
