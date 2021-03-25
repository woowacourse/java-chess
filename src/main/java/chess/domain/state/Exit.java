package chess.domain.state;

public class Exit implements State {

    public static final String ERROR_MESSAGE_IS_ALREADY_EXIT = "[ERROR] 이미 종료된 상태입니다.";

    @Override
    public State exit() {
        throw new IllegalArgumentException(ERROR_MESSAGE_IS_ALREADY_EXIT);
    }

    @Override
    public State init() {
        throw new IllegalArgumentException(ERROR_MESSAGE_IS_ALREADY_EXIT);
    }

    @Override
    public State next() {
        throw new IllegalArgumentException(ERROR_MESSAGE_IS_ALREADY_EXIT);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
