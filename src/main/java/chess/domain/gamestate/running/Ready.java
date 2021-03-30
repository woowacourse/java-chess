package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;
import chess.domain.team.Team;

public class Ready extends Running {

    public Ready(Board board) {
        super(board);
    }

    @Override
    public State changeCommand(CommandType command) {
        validateCommand(command);
        if (command == CommandType.START) {
            return new Start(board);
        }
        return new End(board);
    }

    private void validateCommand(CommandType command) {
        if (command != CommandType.START && command != CommandType.END) {
            throw new IllegalArgumentException("[ERROR] start 혹은 end만 가능합니다.");
        }
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
