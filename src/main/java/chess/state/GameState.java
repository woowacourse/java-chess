package chess.state;

import chess.controller.ChessService;
import chess.model.GameCommand;
import java.util.List;

public interface GameState {

    GameState changeStatus(GameCommand command);

    boolean isEnd();

    GameState execute(ChessService service, List<String> sqaures);

    boolean isStatus();
}
