package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.Position;
import chess.dto.Command;

import static chess.domain.Color.WHITE;

public final class White extends Active {
    public White(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public Active execute(final Command command) {
        final Position source = command.getSource();
        final Position target = command.getTarget();
        chess.move(source, target, WHITE);
        return new Black(gameId, chess);
    }

    @Override
    public Color getCurrentPlayer() {
        return WHITE;
    }
}
