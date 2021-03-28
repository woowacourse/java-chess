package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

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
        if (state.isRunning()) {
            return state.passTurn();
        }
        return state.end();
    }

    public void end() {
        this.state = state.end();
    }

    public Map<Position, Piece> board() {
        return state.squares();
    }

    public String finishReason() {
        return state.finishReason();
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }

    public Color winner() {
        return state.winner();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isInit() {
        return state.isInit();
    }
}
