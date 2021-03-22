package chess.domain.state;

import chess.domain.state.exception.UnsupportedCommandException;

public class End implements State {

    @Override
    public void receive(String command) {
        throw new UnsupportedCommandException("게임이 끝났습니다. 입력받을 수 없습니다.");
    }

    @Override
    public State next() {
        return this;
    }

    @Override
    public Object result() {
        throw new UnsupportedCommandException("게임이 끝났습니다. 결과를 요청할 수 업습니다.");
    }

    @Override
    public ResultType resultType() {
        return ResultType.NONE;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean needsParam() {
        return false;
    }
}
