package chess.state;

import chess.controller.ChessService;
import chess.model.Board;
import chess.model.GameStartCommand;
import java.util.List;

public
class Start extends Running {

    @Override
    public boolean isEnd() {
        return false;
    }

    public Status execute(ChessService service, List<String> sqaures) {
        service.initBoard();
        return new Move();
    }
}
