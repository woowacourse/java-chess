package chess.domain.state;

public class End implements State {
    @Override
    public boolean isReady() {
        return false;
    }
    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public State start() {
        return null;
    }
}
