package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Initializable;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public abstract class State {

    protected Board board;

    public abstract State start();

    public abstract State move(final Position from, final Position to);

    public abstract State end();

    public abstract void status();

    public abstract boolean isRunning();

    public abstract boolean isEnded();

    public final Map<Position, Piece> getBoard() {
        return board.toMap();
    }
}
