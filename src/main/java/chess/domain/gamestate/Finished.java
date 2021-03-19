package chess.domain.gamestate;

import chess.domain.command.Command;
import java.util.List;

public class Finished implements GameState {

    @Override
    public GameState operateCommand(Command command, List<String> arguments) {
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
