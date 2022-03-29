package chess.domain.state.turn;

import chess.domain.Team;

public final class KingDeath extends Finished {

    KingDeath(final Team winner) {
        super(winner);
    }
}
