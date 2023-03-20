package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;

public class End implements State {

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State run(Command command, Board board) {
        if (!command.isEnd()) {
            throw new IllegalArgumentException("start만 입력 가능 합니다.");
        }
        return this;
    }
}
