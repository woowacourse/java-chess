package chess.state;

import chess.controller.ChessService;
import java.util.List;

public class Status extends Running {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public GameState execute(ChessService service, List<String> sqaures) {
        return this;
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
