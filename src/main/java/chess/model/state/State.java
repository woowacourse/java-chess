package chess.model.state;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.piece.Piece;

import java.util.Map;

public interface State {
    boolean isRunning();

    State proceed(String command);

    Map<Position, Piece> getBoard();

    Map<Team, Double> calculateScore();

    boolean isStatus();
}
