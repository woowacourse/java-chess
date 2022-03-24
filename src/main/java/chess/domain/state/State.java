package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public abstract class State {

    protected Board board;

    public abstract State start();

    public abstract State end();

    public final Map<Position, Piece> getBoard() {
        return board.toMap();
    }

    public abstract boolean isRunning();

    public abstract State move(final Position from, final Position to);
}
