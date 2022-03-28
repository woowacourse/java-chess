package chess.state;

import chess.Position;
import chess.piece.Piece;

import java.util.Map;

public interface State {
    boolean isRunning();

    State proceed(String command);

    Map<Position, Piece> getBoard();
}
