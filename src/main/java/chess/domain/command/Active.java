package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Point;
import chess.dto.Command;

public abstract class Active {
    final long gameId;
    final Chess chess;

    Active(final long gameId, final Chess chess) {
        this.gameId = gameId;
        this.chess = chess;
    }

    public Point getPointBy(final Color color) {
        return chess.findPointBy(color);
    }

    public Color getWinner() {
        return chess.findWinner();
    }

    public Chess getChess() {
        return chess;
    }

    public long getGameId() {
        return gameId;
    }

    public Color getCurrentPlayer() {
        return Color.EMPTY;
    }

    public boolean isNotEnd() {
        return true;
    }

    public abstract Active execute(final Command command);
}
