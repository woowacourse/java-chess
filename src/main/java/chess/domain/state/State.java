package chess.domain.state;

import chess.domain.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Map<Location, Piece> getBoard();
}
