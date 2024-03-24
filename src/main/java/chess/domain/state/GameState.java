package chess.domain.state;

import chess.domain.Board;
import chess.domain.position.Position;

public interface GameState {
    GameState start();

    GameState move(Board board, Position source, Position target);

    GameState end();

    boolean isPlaying();
}
