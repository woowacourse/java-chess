package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void move(Position beforePosition, Position afterPosition) {
        state = state.move(beforePosition, afterPosition);
    }

    public void end() {
        state = state.end();
    }

    public double statusOfBlack() {
        return state.statusOfBlack();
    }

    public double statusOfWhite() {
        return state.statusOfWhite();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public Winner findWinner() {
        return state.findWinner();
    }

    public void load(Map<Position, Piece> board, Color turn) {
        state = state.load(board, turn);
    }

    public Color getTurn() {
        return state.getTurn();
    }
}
