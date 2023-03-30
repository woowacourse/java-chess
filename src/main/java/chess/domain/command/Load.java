package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.dto.Command;

public final class Load extends Active {
    private final Color lastPlayer;

    public Load(final long gameId, final Chess chess, final Color lastPlayer) {
        super(gameId, chess);
        this.lastPlayer = lastPlayer;
    }

    @Override
    public Active execute(final Command command) {
        if (lastPlayer.isWhite()) {
            return new Black(gameId, chess).execute(command);
        }
        return new White(gameId, chess).execute(command);
    }
}
