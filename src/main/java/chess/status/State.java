package chess.status;

import chess.Board;
import chess.view.Command;

public interface State {

    State turn(Command command);

    boolean isRunning();

    void move(String command);

    boolean canMove();

    Board getBoard();
}
