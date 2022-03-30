package chess.domain.game;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.result.Score;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import java.util.Map;

public class ChessGame {

    private State state;
    private final Board board;

    public ChessGame() {
        this.board = new Board(BoardInitializer.create());
        this.state = new Ready(board);
    }

    public void start() {
        state = state.start();
    }

    public void move(final Position from, final Position to) {
        state = state.move(from, to);
    }
    public void status() {
        state = state.status();
    }

    public void end() {
        state = state.end();
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

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
