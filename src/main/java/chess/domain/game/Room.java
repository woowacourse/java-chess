package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.dto.ScoreDto;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.team.Team;

public class Room {

    private final long id;
    private final String name;
    private State state;
    private Team currentTeam;

    public Room(long id, String name, State state, Team currentTeam) {
        this.id = id;
        this.name = name;
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

    public ScoreDto judgeResult() {
        return state.judgeResult();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public State getState() {
        return state;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
