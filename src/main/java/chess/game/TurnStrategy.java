package chess.game;

import chess.domain.Team;

public interface TurnStrategy {
    Team create();
}
