package chess.domain.game;

import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.result.Score;
import java.util.Map;

public class ChessGame {

    private State state;
    private Board board;

    public ChessGame() {
        this.board = new Board(new BasicBoardFactory());
        this.state = new Ready(board);
    }

    public void initialize() {
        board = new Board(new BasicBoardFactory());
        state = new Ready(board);
    }

    public void load(final State state, final Board board) {
        this.state = state;
        this.board = board;
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

    public String getTurnName() {
        return state.getTurn().getName();
    }

    public String getOppositeTurnName() {
        return state.getTurn().getOpposite().getName();
    }

    public Board board() {
        return board;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public State getState() {
        return state;
    }
}
