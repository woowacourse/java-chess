package chess.domain.state;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.point.Point;
import chess.dto.Command;

import java.util.Map;

public abstract class State {
    protected final Chess chess;

    State(final Chess chess) {
        this.chess = chess;
    }

    public abstract State start();
    public abstract State move(final Command command);
    public abstract State end();
    public abstract State status();
    public abstract boolean isNotEnd();

    public final Map<Position, Piece> getChess() {
        return Map.copyOf(chess.getBoardValue());
    }

    public final Color getWinner() {
        return chess.findWinner();
    }

    public final Point getPointBy(final Color color) {
        return chess.findPointBy(color);
    }
}
