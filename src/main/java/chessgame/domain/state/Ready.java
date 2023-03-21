package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;

public class Ready implements State {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(Command command, Board board) {
        if (command.isStart()) {
            return new White();
        }
        if (command.isMove()) {
            throw new IllegalArgumentException("move를 입력할 수 없습니다.");
        }
        if (command.isEnd()) {
            return new End();
        }
        return new Ready();
    }
}
