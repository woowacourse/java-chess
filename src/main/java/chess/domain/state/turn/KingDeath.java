package chess.domain.state;

import chess.domain.piece.Team;

public final class KingDeath extends Finished {

    KingDeath(final Team winner) {
        super(winner);
    }
}
