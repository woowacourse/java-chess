package chess.domain.gamestate.finished;

import chess.domain.board.Board;
import chess.domain.gamestate.AbstractState;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;

public class End extends AbstractState {

    public End(Board board) {
        super(board);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State processCommand(CommandType command) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }
}
