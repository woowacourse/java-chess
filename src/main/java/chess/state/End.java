package chess.state;

import chess.controller.ChessService;
import chess.model.GameStartCommand;
import java.util.List;

public class End implements GameState {

    @Override
    public GameState changeStatus(GameStartCommand command) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public GameState execute(ChessService service, List<String> squares) {
        return this;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
