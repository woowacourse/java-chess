package chess.domain.game;

import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.result.Score;

public class ChessGame {

    private State state;
    private Board board;

    public ChessGame(final State state, final Board board) {
        this.state = state;
        this.board = board;
    }

    public ChessGame() {
        this.board = new Board(new BasicBoardFactory());
        this.state = new Ready(board);
    }

    public void initialize() {
        board = new Board(new BasicBoardFactory());
        state = new Ready(board);
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void move(final Position from, final Position to) {
        state = state.move(from, to);
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
