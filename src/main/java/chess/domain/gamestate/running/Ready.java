package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.dto.ResponseDto;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;

public class Ready extends Running {

    public Ready(Board board, String commandInput) {
        super(board, commandInput);
    }

    @Override
    public State changeCommand(CommandType command, String commandInput) {
        validateCommand(command);
        if (command == CommandType.START) {
            return new Start(board, commandInput);
        }
        return new End(board, commandInput);
    }

    private void validateCommand(CommandType command) {
        if (command != CommandType.START && command != CommandType.END) {
            throw new IllegalArgumentException("[ERROR] start 혹은 end만 가능합니다.");
        }
    }

    @Override
    public ResponseDto getProcessResult() {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전 입니다.");
    }

    @Override
    public boolean isMove() {
        return false;
    }
}
