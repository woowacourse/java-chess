package chess.state;

public class Finished implements State {

    @Override
    public State start() {
        return alertFinished();
    }

    @Override
    public State move(String from, String to) {
        return alertFinished();
    }

    @Override
    public State status() {
        return alertFinished();
    }

    @Override
    public State end() {
        return alertFinished();
    }

    private Finished alertFinished() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }
}
