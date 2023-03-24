package chess.repository;

import chess.domain.TeamColor;
import chess.domain.position.Position;

public interface GameService {

    void updateMovement(final Position source, final Position dest, final long gameId, final boolean isDestDeleteNeeded);

    void updateGameStatusEnd(final long gameId);

    void updateGameTurn(final long gameId, final TeamColor teamColor);

}
