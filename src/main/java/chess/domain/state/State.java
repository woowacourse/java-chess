package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.StateType;

import java.util.Map;
import java.util.function.ObjDoubleConsumer;

public abstract class State {

    protected Board board;
    protected StateType stateType;

    public abstract State start();

    public abstract State move(final Position from, final Position to);

    public abstract State end();

    public abstract void status(final ObjDoubleConsumer<String> printScore);

    public abstract boolean isRunning();

    public abstract boolean isEnded();

    public Map<Position, Piece> getBoard() {
        return board.toMap();
    }

    public StateType getStateType() {
        return this.stateType;
    }
}
