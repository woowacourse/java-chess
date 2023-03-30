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
    public State run(Command command, Board board) {
        if (command.isEnd()) {
            return new End();
        }
        if (command.isMove()) {
            board.move(command.getSourcePoint(), command.getTargetPoint(), Team.BLACK);
            return selectNextState(board);
        }
        return this;
    }

    private State selectNextState(Board board) {
        if (!board.isExistKing(Team.WHITE)) {
            return new End();
        }
        return new White();
    }
}
