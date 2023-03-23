package chess;

import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.board.Board;
import chess.view.Command;

public class ChessGame {

    private final Board board;
    private State state;

    public ChessGame() {
        this.board = Board.create();
        this.state = new Ready();
    }

    public Board getBoard() {
        return board;
    }

    public State getState() {
        return state;
    }

    public void setState(Command command) {
        state = state.progress(command,board);
    }

    public boolean isEnd(){
        return state.isEnd();
    }
}
