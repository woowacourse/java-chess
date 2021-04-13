package chess.domain.gamestate.finished;

import chess.domain.board.Board;
import chess.domain.gamestate.AbstractState;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.team.Team;

public class End extends AbstractState {

    public End(Board board) {
        super(board);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State changeCommand(CommandType command) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public void processMove(String input, Team currentTeam) {
        throw new IllegalArgumentException("[ERROR] 현재 move 상태가 아닙니다.");
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public String getValue() {
        return "end";
    }
}
