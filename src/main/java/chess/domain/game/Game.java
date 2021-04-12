package chess.domain.game;

import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.Init;
import chess.domain.state.State;
import chess.domain.state.Status;

import java.util.Map;

public class Game {
    private final Board board;
    private final Calculator calculator = new Calculator();
    private State state;

    public Game(Board board) {
        this.board = board;
        state = new Init();
    }

    public void move(Position from, Position to) {
        board.action(state.color(), from, to);
        if (board.isKingDead()) {
            end();
            return;
        }
        state = state.opposite();
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void status() {
        state = state.status();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public boolean isNotEnd() {
        return !isEnd();
    }

    public boolean isStatus() {
        return state instanceof Status;
    }

    public Color currentPlayer() {
        return state.color();
    }

    public Map<Color, Double> score() {
        return calculator.scoreByColor(board.allPieces());
    }

    public double score(Color color) {
        return calculator.score(color, board.allPieces());
    }

    public Map<Position, Piece> allBoard() {
        return board.allPieces();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public State finish() {
        return state.finish();
    }
}
