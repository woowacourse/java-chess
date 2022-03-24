package chess.status;

import chess.Board;
import chess.MoveCommand;
import chess.view.Command;

public interface State {

    State turn(Command command);

    boolean isRunning();

    void move(MoveCommand moveCommand);

    boolean canMove();

    Board getBoard();
}
