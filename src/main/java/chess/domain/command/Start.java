package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.dto.Command;

public final class Start extends Active {
    public Start(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public Active execute(final Command command) {
        return new White(gameId, chess);
    }

    @Override
    public Color getCurrentPlayer() {
        return Color.WHITE;
    }
}
