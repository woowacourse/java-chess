package chess.controller.request;

import chess.controller.ChessController;
import java.util.List;

public class EndRequestType implements RequestType {

    @Override
    public boolean isMatch(List<String> commands) {
        if (commands.size() != 1) {
            return false;
        }
        return "end".equalsIgnoreCase(commands.get(0));
    }

    @Override
    public void execute(ChessController chessController, List<String> request) {
        chessController.finish();
    }
}
