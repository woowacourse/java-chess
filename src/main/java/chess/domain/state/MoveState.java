package chess.domain.state;

public abstract class MoveState implements GameState {
    public GameState start() {
        throw new UnsupportedOperationException("이미 시작한 상태입니다.");
    }

    public GameState end() {
        return new EndState();
    }

    public boolean isPlaying() {
        return true;
    }
}
