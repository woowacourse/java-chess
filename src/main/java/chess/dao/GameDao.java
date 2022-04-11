package chess.dao;

import java.util.List;

import chess.Game;

public interface GameDao {

    void save(Game game);

    void deleteById(int id);

    List<String> findById(int id);

    String findTurnById(int id);

    int findIdByPlayers(String idPlayerWhite, String idPlayerBlack);
}
