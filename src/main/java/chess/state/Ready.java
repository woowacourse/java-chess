package chess.state;

import chess.controller.ChessService;
import chess.model.Board;
import chess.model.GameStartCommand;
import java.util.List;


public class Ready implements Status {

    @Override
    public Status changeStatus(GameStartCommand command) {
        if (command.isStart()) {
            return new Start();
        }
        if (command.isMove()) {
            return new Move();
        }
        return new End();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public Status execute(ChessService service, List<String> sqaures) {
        return this;
    }
}
