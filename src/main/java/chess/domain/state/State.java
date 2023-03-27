package chess.domain.state;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.point.Point;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;
import java.util.Map;

public abstract class State {
    protected final long gameId;
    protected final Chess chess;

    State(final long gameId, final Chess chess) {
        this.gameId = gameId;
        this.chess = chess;
    }

    public abstract State start(final long gameId);
    public abstract State move(final Command command);
    public abstract State end();
    public abstract State status();
    public abstract State loadList();
    public abstract State load(final List<MovementDto> movements);
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

    public Color getCurrentPlayer() {
        return Color.EMPTY;
    }
}
