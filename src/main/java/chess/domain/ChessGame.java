package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.List;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public List<Piece> getPiecesByAllPosition() {
        return state.allPieces();
    }

    public void movePieceFromSourceToTarget(Position source, Position target) {
        changeState(state.checkRunnable());
        state.movePieceFromSourceToTarget(source, target);
        changeState(state.next());
    }

    public ScoreStatus scoreStatus() {
        return state.scoreStatus();
    }

    private void changeState(State state) {
        System.out.println(state);
        this.state = state;
    }

    public void start() {
        changeState(state.start());
    }

    public boolean runnable() {
        return !state.isFinish();
    }

    public boolean notStartYet() {
        return state.isReady();
    }

    public void end() {
        changeState(state.end());
    }
}
