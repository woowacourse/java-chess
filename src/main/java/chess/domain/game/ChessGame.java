package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.dto.ResponseDto;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.running.Ready;
import chess.domain.team.Team;
import chess.utils.BoardUtil;

public class ChessGame {

    private Team currentTeam;
    private State state;

    public ChessGame() {
        Board board = BoardUtil.generateInitialBoard();
        this.currentTeam = Team.WHITE;
        this.state = new Ready(board);
    }

    public void play(String input) {
        CommandType command = CommandType.from(input);
        State nextState = state.changeCommand(command);
        if (command == CommandType.MOVE) {
            nextState.processMove(input, currentTeam);
            currentTeam = currentTeam.reverse();
        }

        if (state.isFinished()) {
            state = state.changeCommand(CommandType.END);
        }
        state = nextState;
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public ResponseDto getProcessResult() {
        return state.getProcessResult();
    }
}
