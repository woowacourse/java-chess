package chess.domain.state;

public class EndState implements GameState {
    @Override
    public GameState start() {
        throw new UnsupportedOperationException("종료 상태에서는 시작할 수 없습니다.");
    }

    @Override
    public GameState move() {
        throw new UnsupportedOperationException("종료 상태에서는 움직일 수 없습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("종료 상태에서는 종료할 수 없습니다.");
    }
}
