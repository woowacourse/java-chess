package chess.state;

import chess.game.Board;
import chess.game.Command;
import chess.game.MoveCommand;
import chess.game.Score;
import chess.piece.Color;

public interface State {

    void move(MoveCommand moveCommand);

    State turn(Command command);

    Score score();

    boolean isRunning();

    boolean canMove();

    boolean isGameEnd();

    Board getBoard();

    Color getColor();
}
