package chess.dao;

import chess.Game;

public interface GameDao {

    void save(Game game);

    void deleteById(int id);

    String findTurnById(int id);

    int findIdByPlayerNames(String idPlayerWhite, String idPlayerBlack);
}
