package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.State;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class ChessGame {
    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void move(Position source, Position target) {
        state = state.move(source, target);
    }

    public void end() {
        state = state.end();
    }

    public Board board() {
        return state.board();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Status status() {
        Score whiteScore = state.score(Color.WHITE);
        Score blackScore = state.score(Color.BLACK);
        return new Status(whiteScore, blackScore);
    }
}
