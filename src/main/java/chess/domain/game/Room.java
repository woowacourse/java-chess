package chess.domain.game;

import chess.domain.dto.ScoreDto;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.team.Team;

public class Room {

    private State state;
    private Team currentTeam;

    public Room(State state, Team currentTeam) {
        this.state = state;
        this.currentTeam = currentTeam;
    }

    public void play(String input) {
        CommandType command = CommandType.from(input);
        State nextState = state.changeCommand(command);
        commandMoveState(command, nextState, input);
        if (state.isAnyKingDead()) {
            state = state.changeCommand(CommandType.END);
            return;
        }

        if (state.isFinished()) {
            state = state.changeCommand(CommandType.END);
            return;
        }
        state = nextState;
    }

    private void commandMoveState(CommandType command, State nextState, String input) {
        if (command == CommandType.MOVE) {
            nextState.processMove(input, currentTeam);
            currentTeam = currentTeam.reverse();
        }
    }

    public State state() {
        return state;
    }

    public Team currentTeam() {
        return currentTeam;
    }

    public ScoreDto judgeResult() {
        return state.judgeResult();
    }
}
