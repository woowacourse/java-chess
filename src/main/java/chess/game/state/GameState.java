package chess.game.state;

import chess.board.Board;
import chess.position.Position;

public interface GameState {

    GameState start();

    GameState proceedTurn(Board board, Position source, Position destination);

    GameState terminate();

    boolean isPlaying();

    default void validatePlaying() {
        if (!isPlaying()) {
            throw new UnsupportedOperationException("게임이 진행되고 있지 않습니다.");
        }
    }
}
