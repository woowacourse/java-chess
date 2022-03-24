package chess.domain.state;

public class White extends State {

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
