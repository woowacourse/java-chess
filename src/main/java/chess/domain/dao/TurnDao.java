package chess.domain.dao;

import chess.domain.Color;

public interface TurnDao {

    void update(final Color color);

    void create(final Color color);

    Color getCurrentTurn();

}
