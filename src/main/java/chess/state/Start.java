package chess.state;

import chess.controller.ChessService;
import java.util.List;

public class Start extends Running {

    @Override
    public boolean isEnd() {
        return false;
    }

    public GameState execute(ChessService service, List<String> sqaures) {
        service.initBoard();
        return new Move();
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
