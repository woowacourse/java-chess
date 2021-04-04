package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.List;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready(new Pieces(PieceFactory.initialPieces()));
    }

    public ChessGame(State state) {
        this.state = state;
    }

    public List<Piece> getPiecesByAllPosition() {
        return state.allPieces();
    }

    public void movePieceFromSourceToTarget(Position source, Position target) {
        state.movePieceFromSourceToTarget(source, target);
        changeState(state.next());
    }

    public ScoreStatus scoreStatus() {
        return state.scoreStatus();
    }

    private void changeState(State state) {
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

    public Color turn() {
        return state.color();
    }
}
