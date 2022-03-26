package chess.state;

import chess.controller.ChessService;
import chess.model.GameStartCommand;
import java.util.List;

public interface GameState {

    GameState changeStatus(GameStartCommand command);

    boolean isEnd();

    GameState execute(ChessService service, List<String> sqaures);

    boolean isStatus();
}
