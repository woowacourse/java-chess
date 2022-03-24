package chess.domain.state;

public class End extends State {

    @Override
    public State start() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임이 이미 종료됐습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
