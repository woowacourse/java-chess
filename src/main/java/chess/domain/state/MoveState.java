package chess.domain.state;

import chess.domain.Board;
import chess.domain.position.Position;

public abstract class MoveState implements GameState {
    public GameState start() {
        throw new UnsupportedOperationException("이미 시작한 상태입니다.");
    }

    public abstract GameState move(Board board, Position source, Position target);

    public GameState end() {
        return new EndState();
    }

    public boolean isPlaying() {
        return true;
    }
}
