package chess.dao;

import java.sql.Connection;
import java.util.List;

import chess.Game;

public interface GameDao {
    Connection getConnection();

    void save(Game game);

    void deleteById(int id);

    List<String> findById(int id);

    String findTurnById(int id);

    int findByIds(String idPlayerWhite, String idPlayerBlack);
}
