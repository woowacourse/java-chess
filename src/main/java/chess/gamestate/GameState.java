package chess.gamestate;

import chess.command.Command;
import java.util.List;

public interface GameState {
    GameState operateCommand(Command command, List<String> arguments);
    boolean isRunning();
}
