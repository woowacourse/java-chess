package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public abstract class State {
    private final Board board;
    private final Color color;

    protected State(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    public abstract State move(Position source, Position target);

    public abstract State start();

    public abstract State end();

    protected final Color color() {
        return color;
    }

    protected final Board board() {
        return board;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Color getColor() {
        return color;
    }
}
