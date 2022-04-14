package chess.state;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Position;
import chess.view.Command;
import java.util.Map;

public interface State {

    State turn(Command command);

    boolean isRunning();

    void move(MoveCommand moveCommand);

    boolean canMove();

    Board getBoard();

    boolean isGameEnd();

    Color getTurn();

    Map<Color, Double> getStatus();

    void load(Map<Position, Piece> board);
}
