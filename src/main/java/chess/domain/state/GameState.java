package chess.domain.state;

import chess.domain.grid.ChessGame;
import chess.domain.position.Position;

public interface GameState {
    GameState start();

    GameState status();

    GameState end();

    GameState move(final ChessGame game, final Position sourcePosition, final Position targetPosition);

    boolean isFinished();
}
