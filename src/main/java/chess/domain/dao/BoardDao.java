package chess.domain.dao;

import chess.domain.Color;

public interface BoardDao {

    int save(Color turn);

    Color findTurn();

    void deleteBoard();
}
