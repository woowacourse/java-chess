package chess.domain.gamestate.finished;

import chess.domain.board.Board;
import chess.domain.dto.ResponseDto;
import chess.domain.gamestate.AbstractState;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;

public class End extends AbstractState {

    public End(Board board, String commandInput) {
        super(board, commandInput);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State changeCommand(CommandType command, String commandInput) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public ResponseDto getProcessResult() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }



    @Override
    public boolean isMove() {
        return false;
    }
}
