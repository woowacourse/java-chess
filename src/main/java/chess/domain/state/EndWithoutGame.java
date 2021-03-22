package chess.domain.state;

public class EndWithoutGame extends DefaultState {
    public EndWithoutGame() {
        super();
    }

    @Override
    public State start() {
        throw new IllegalStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State status() {
        throw new IllegalStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State move(boolean isKingDead) {
        throw new IllegalStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public boolean isNotRunning() {
        throw new IllegalStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }
}
