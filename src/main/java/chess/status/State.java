package chess.status;

import chess.Board;
import chess.MoveCommand;
import chess.piece.Color;
import chess.view.Command;
import java.util.Map;

public interface State {

    State turn(Command command);

    boolean isRunning();

    void move(MoveCommand moveCommand);

    boolean canMove();

    Board getBoard();

    boolean isGameEnd();

    Color getColor();

    Map<Color, Double> getStatus();
}
