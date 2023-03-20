package chess.controller.request;

import chess.controller.ChessController;
import java.util.List;

public class StartRequestType implements RequestType {

    @Override
    public boolean isMatch(List<String> commands) {
        if (commands.size() != 1) {
            return false;
        }
        return "start".equalsIgnoreCase(commands.get(0));
    }

    @Override
    public final void execute(ChessController chessController, List<String> commands) {
        chessController.start();
    }
}
