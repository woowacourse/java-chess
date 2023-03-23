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
            throw new UnsupportedOperationException("start를 입력할 수 없습니다.");
        }
        if (command.isEnd()) {
            return new End();
        }
        if (command.isMove()) {
            board.move(command.getSourcePoint(), command.getTargetPoint(), Team.WHITE);
            return selectNextState(board);
        }
        return this;
    }

    private State selectNextState(Board board) {
        if(!board.isExistKing(Team.BLACK)){
            return new End();
        }
        return new Black();
    }
}
