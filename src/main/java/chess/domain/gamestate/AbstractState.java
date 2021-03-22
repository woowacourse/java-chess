package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.team.Team;
import chess.utils.BoardUtil;

public abstract class AbstractState implements State {

    protected final Board board;
    protected final String commandInput;

    public AbstractState(Board board, String commandInput) {
        this.board = board;
        this.commandInput = commandInput;
    }

    @Override
    public void processCommand(Team currentTurn) {

    }
}
