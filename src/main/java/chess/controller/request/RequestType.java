package chess.controller.request;

import chess.controller.ChessController;
import java.util.List;

public interface RequestType {

    boolean isMatch(List<String> commands);

    void execute(ChessController chessController, List<String> commands);
}
