package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.List;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void move(String source, String target) {
        state = state.move(source, target);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double score(Color color) {
        ChessBoard chessBoard = state.chessBoard();
        return chessBoard.calculateScore(color);
    }

    public Result result() {
        return state.winner();
    }

    public List<List<Piece>> board() {
        ChessBoard chessBoard = state.chessBoard();
        return chessBoard.getBoard();
    }
}
