package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.State;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.result.Score;

public class ChessGame {

    private final Board board;
    private State state;

    public ChessGame(final State state, final Board board) {
        this.board = board;
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void move(final Position from, final Position to) {
        state = state.move(board, from, to);
    }

    public void status() {
        state = state.status();
    }

    public Score calculateMyScore() {
        return new Score(board, state.getTurn());
    }

    public Score calculateOpponentScore() {
        return new Score(board, state.getTurn().getOpposite());
    }

    public boolean isNotEnded() {
        return state.isNotEnded();
    }

    public boolean isStarted() {
        return state.isStarted();
    }

    public State getState() {
        return state;
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return state.getTurn();
    }
}
