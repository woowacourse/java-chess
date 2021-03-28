package chess.domain.state;

public class BlackTurn implements State {
    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 이미 게임을 시작했습니다.");
    }
}
