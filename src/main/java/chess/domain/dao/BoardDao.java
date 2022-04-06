package chess.domain.dao;

import chess.domain.Color;

public interface BoardDao {

    int save(Color turn);

    Color getCurrentTurn();

    void deleteBoard();
}
