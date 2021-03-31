package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import java.util.List;

public class ChessGame {

    private State state;

    public ChessGame(Board board) {
        this.state = new Init(board);
    }

    public void start() {
        this.state = state.start();
    }

    public void move(Position source, Position target) {
        this.state.moveIfValidColor(source, target);
        this.state = state.passTurn();
    }

    public void end() {
        this.state = state.end();
    }

    public Board board() {
        return state.board();
    }

    public boolean isInit() {
        return state.isInit();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public String winner() {
        return state.winner();
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }

    public List<Position> movablePath(final Position position) {
        return this.state.movablePath(position);
    }

    public State state() {
        return this.state.state();
    }
}
