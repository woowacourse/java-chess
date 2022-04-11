package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public abstract class State {

    protected Board board;

    public abstract State start();

    public abstract boolean isEnd();

    public abstract State move(final Position from, final Position to);

    public abstract double score(final Color color);

    public abstract Result getWinner();

    public abstract State end();

    public final Map<Position, Piece> getBoard() {
        return board.getValue();
    }
}
