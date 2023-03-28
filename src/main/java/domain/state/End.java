package domain.state;

import java.util.List;

public class End implements State {

    public End() {
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State run(List<String> command) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }
}
