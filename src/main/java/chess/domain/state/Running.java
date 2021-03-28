package chess.domain.state;

public class Running implements State {
    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 진행중입니다.");
    }

    @Override
    public State end() {
        return new End();
    }
}
