package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.dto.ResponseDto;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;
import chess.domain.team.Team;
import chess.utils.BoardUtil;

public class Status extends Running {

    public Status(Board board, String commandInput) {
        super(board, commandInput);
    }

    @Override
    public State changeCommand(CommandType command, String commandInput) {
        validateCommand(command);
        if (command == CommandType.MOVE) {
            return new Move(board, commandInput);
        }
        return new End(board, commandInput);
    }

    private void validateCommand(CommandType command) {
        if (command != CommandType.MOVE && command != CommandType.END) {
            throw new IllegalArgumentException("[ERROR] move와 end만 가능합니다.");
        }
    }

    @Override
    public ResponseDto getProcessResult() {
        return new ResponseDto.Builder(BoardUtil.generateViewBoard(board))
            .blackScore(board.score(Team.BLACK))
            .whiteScore(board.score(Team.WHITE))
            .build();
    }

    @Override
    public boolean isMove() {
        return false;
    }
}
