package chess.controller.request;

import chess.controller.ChessController;
import java.util.List;
import java.util.regex.Pattern;

public final class MoveRequestType implements RequestType {

    private static final Pattern POSITION = Pattern.compile("[a-h][1-8]");

    @Override
    public boolean isMatch(List<String> commands) {
        if (commands.size() != 3) {
            return false;
        }
        if (!"move".equalsIgnoreCase(commands.get(0))) {
            return false;
        }
        return isPosition(commands.get(1)) && isPosition(commands.get(2));
    }

    @Override
    public final void execute(ChessController chessController, List<String> commands) {
        chessController.move(commands);
    }

    private boolean isPosition(String command) {
        return POSITION.matcher(command).matches();
    }
}
