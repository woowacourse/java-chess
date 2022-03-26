package chess.state;

import chess.controller.ChessService;
import chess.model.Board;
import chess.model.GameStartCommand;
import java.util.List;

public class End implements Status {

    @Override
    public Status changeStatus(GameStartCommand command) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Status execute(ChessService service, List<String> squares) {
        return this;
    }
}
