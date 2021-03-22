package chess.domain.gamestate.running;

import static chess.utils.BoardUtil.generateViewBoard;

import chess.domain.board.Board;
import chess.domain.dto.ResponseDto;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;
import chess.domain.location.Location;
import chess.domain.team.Team;

public class Move extends Running {

    public Move(Board board, String commandInput) {
        super(board, commandInput);
    }

    @Override
    public State changeCommand(CommandType command, String commandInput) {
        validateCommand(command);
        if (command == CommandType.STATUS) {
            return new Status(board, commandInput);
        }
        if (command == CommandType.MOVE) {
            return this;
        }
        return new End(board, commandInput);
    }

    public void validateCommand(CommandType command) {
        if (command != CommandType.STATUS && command != CommandType.END && command != CommandType.MOVE) {
            throw new IllegalArgumentException("[ERROR] status, move, end만 가능합니다.");
        }
    }

    @Override
    public ResponseDto getProcessResult() {
        return new ResponseDto.Builder(generateViewBoard(board))
            .blackScore(-1)
            .whiteScore(-1)
            .build();
    }

    @Override
    public void processCommand(Team currentTurn) {
        String[] splited = commandInput.split(" ");
        Location source = Location.of(splited[1]);
        Location target = Location.of(splited[2]);
        board.move(source, target, currentTurn);
    }

    @Override
    public boolean isMove() {
        return true;
    }
}
