package chess.dao;

import java.sql.Connection;
import java.util.List;

public interface GameDao {
    Connection getConnection();

    void save();

    void deleteById(int id);

    int getId();

    List<String> findById(int id);

    String findTurnById(int id);

    void nextTurn();

    int findByIds(String idPlayerWhite, String idPlayerBlack);
}
