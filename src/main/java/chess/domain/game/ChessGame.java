package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.domain.game.state.WhiteTurn;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

import java.util.Map;
import java.util.Set;

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

    public Set<Position> movablePath(Position source) {
        return state.movablePath(source);
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

    public State state() {
        return state;
    }

    public void setState(String turn) {
        if (BlackTurn.BLACK_TURN.equals(turn)) {
            state = new BlackTurn(new Board(board()));
            return;
        }
        state = new WhiteTurn(new Board(board()));
    }

    public void load(ChessGame loadChessGame) {
        state = loadChessGame.state;
    }
}
