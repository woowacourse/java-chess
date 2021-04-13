package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.team.Team;

public class Ready extends Running {

    public Ready(Board board) {
        super(board);
    }

    @Override
    public State changeCommand(CommandType command) {
        if (command == CommandType.START) {
            return new Start(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임을 시작해주세요.");
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
        return "ready";
    }
}
