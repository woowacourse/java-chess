package chess.state;

import chess.controller.ChessService;
import chess.model.Board;
import chess.model.GameStartCommand;
import java.util.List;

public interface Status {

//    private final Board board;

    Status changeStatus(GameStartCommand command);

    boolean isEnd();

    Status execute(ChessService service, List<String> sqaures);

}
