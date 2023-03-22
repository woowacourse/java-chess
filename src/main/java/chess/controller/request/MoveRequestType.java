package chess.controller.request;

import chess.controller.ChessController;
import java.util.regex.Pattern;

public abstract class MoveRequestType implements RequestType {

    private static final Pattern POSITION = Pattern.compile("[a-h][1-8]");

    private final String origin;
    private final String destination;

    protected MoveRequestType(String origin, String destination) {
        if (isNotPosition(origin) || isNotPosition(destination)) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }
        this.origin = origin;
        this.destination = destination;
    }

    private boolean isNotPosition(String command) {
        return !POSITION.matcher(command).matches();
    }

    @Override
    public final void execute(ChessController chessController) {
        chessController.move(origin, destination);
        chessController.printBoard();
    }
}
