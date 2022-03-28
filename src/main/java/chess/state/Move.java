package chess.state;

import chess.controller.ChessService;
import java.util.List;

public final class Move extends Running {

    @Override
    public boolean isEnd() {
        return false;
    }

    public GameState execute(ChessService service, List<String> sqaures) {
        String from = sqaures.get(0);
        String to = sqaures.get(1);
        service.move(from, to);
        if (!service.checkKingsAlive()) {
            return new End();
        }
        return this;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}

