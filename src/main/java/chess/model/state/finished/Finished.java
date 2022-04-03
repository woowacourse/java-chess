package chess.model.state.finished;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.State;
import chess.model.state.running.WhiteTurn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Finished implements State {

    protected final Board board;

    protected Finished(Board board) {
        this.board = board;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State proceed(List<String> inputs) {
        return new WhiteTurn(new Board());
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return new HashMap<>();
    }
}
