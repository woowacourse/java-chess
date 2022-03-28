package chess.status;

import chess.game.Board;
import chess.game.Command;
import chess.game.MoveCommand;
import chess.piece.Color;
import java.util.Map;

public interface State {

    void move(MoveCommand moveCommand);

    State turn(Command command);

    Map<Color, Double> score();

    boolean isRunning();

    boolean canMove();

    boolean isGameEnd();

    Board getBoard();

    Color getColor();
}
