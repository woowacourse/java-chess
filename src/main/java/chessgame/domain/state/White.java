package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;
import chessgame.domain.Team;

public class White implements State {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(Command command, Board board) {
        if (command.isStart()) {
            throw new IllegalArgumentException("start를 입력할 수 없습니다.");
        }
        if (command.isMove()) {
            board.move(command.getSourcePoint(), command.getTargetPoint(), Team.WHITE);
            return new Black();
        }
        if (command.isEnd()) {
            return new End();
        }
        return this;
    }
}
