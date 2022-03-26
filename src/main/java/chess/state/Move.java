package chess.state;

import chess.controller.ChessService;
import chess.model.Board;
import chess.model.GameStartCommand;
import chess.model.Square;
import java.util.List;

public class Move extends Running {

    @Override
    public boolean isEnd() {
        return false;
    }

    public Status execute(ChessService service, List<String> sqaures) {
        String from = sqaures.get(0);
        String to = sqaures.get(1);
        service.move(from, to);
        if (!service.checkKingsAlive()) {
            return new End();
        }
        return this;
    }
}

