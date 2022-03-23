package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public abstract class Running implements State{
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    public Map<Location, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public State start() {
        return null;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State end() {
        return new End();
    }
}
