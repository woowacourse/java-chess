package domain;

import domain.piece.objects.Piece;
import domain.piece.position.Position;
import domain.score.Score;
import domain.state.Running;
import domain.state.State;
import domain.state.Wait;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private State state;

    public ChessGame() {
        state = new Wait(new HashMap<>());
    }

    public void load(Map<Position, Piece> pieces, boolean turn) {
        state = new Running(pieces, turn);
    }

    public void start(Map<Position, Piece> pieces) {
        state = state.run(pieces);
    }

    public void move(Position start, Position end) {
        state = state.move(start, end);
    }

    public double blackScore() {
        return state.blackScore().getValue();
    }

    public double whiteScore() {
        return state.whiteScore().getValue();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public void finish() {
        state = state.finish();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean getTurn() {
        return state.getTurn();
    }

    public boolean isEnd() {
        return state.isEnd();
    }
}
