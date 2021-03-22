package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

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
        this.state = stateByFinished();
    }

    private State stateByFinished() {
        if (state.isNotFinished()) {
            return state.passTurn();
        }
        return state.end();
    }

    public void end() {
        this.state = state.end();
    }

    public List<Map<Position, Piece>> board() {
        return state.squares();
    }

    public String finishReason() {
        return state.finishReason();
    }

    public boolean isFinished() {
        return !state.isNotFinished();
    }

    public boolean isNotFinished() {
        return state.isNotFinished();
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }
}
