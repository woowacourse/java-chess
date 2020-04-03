package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.DefaultBoardInitializer;
import chess.domain.player.Player;
import chess.domain.result.Status;
import chess.domain.state.ReadyState;
import chess.domain.state.State;

public class ChessGame {
    private State state;
    private Turn turn = Turn.from(Player.WHITE);

    public ChessGame() {
        this.state = new ReadyState(new DefaultBoardInitializer());
    }

    public void start() {
        state = state.start();
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter, turn);
    }

    public void end() {
        state = state.end();
    }

    public void status() {
        state = state.status();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Status getStatus() {
        return state.getStatus();
    }
}
