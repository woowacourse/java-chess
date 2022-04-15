package chess.state;

import chess.chessboard.position.Position;
import chess.piece.Piece;
import chess.game.Player;

import java.util.Map;

public interface State {
    boolean isRunning();

    State proceed(final String command);

    Map<Position, Piece> getBoard();

    boolean isStatus();

    Player getPlayer();
}
