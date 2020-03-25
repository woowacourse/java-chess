package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.EnumRepositoryBoardInitializer;
import chess.domain.state.ReadyState;
import chess.domain.state.State;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new ReadyState(new EnumRepositoryBoardInitializer());
    }

    public void start() {
        state = state.start();
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter.getSource(), moveParameter.getTarget());
    }

    public void end() {
        state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
