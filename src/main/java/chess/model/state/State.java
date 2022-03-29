package chess.model.state;

import chess.controller.Command;
import chess.model.Team;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public interface State {

    boolean isFinished();

    boolean isSleep();

    Map<Team, Double> getScores();

    Map<Position, Piece> getBoard();

    State execute(Command command);
}
