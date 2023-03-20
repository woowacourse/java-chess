package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;
import chessgame.domain.Team;

public class Black implements State {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State click(Command command, Board board) {
        if (command.isStart()) {
            throw new IllegalArgumentException("시작을 입력할 수 없습니다.");
        }
        if (command.isMove()) {
            board.move(command.makePoints().get(0), command.makePoints().get(1), Team.BLACK);
            return new White();
        }
        if (command.isEnd()) {
            return new End();
        }
        return this;
    }
}
