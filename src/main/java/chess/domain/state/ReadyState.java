package chess.domain.state;

public class ReadyState implements GameState {
    @Override
    public GameState start() {
        return new StartState();
    }

    @Override
    public GameState move() {
        throw new UnsupportedOperationException("준비 상태에서는 움직일 수 없습니다.");
    }

    @Override
    public GameState end() {
        return new EndState();
    }

    @Override
    public boolean isPlaying() {
        return true;
    }
}
