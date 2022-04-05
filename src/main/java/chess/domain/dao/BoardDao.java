package chess.domain.dao;

import chess.domain.Color;

public interface BoardDao {

    void save(Color turn);

    Color getCurrentTurn();
}
