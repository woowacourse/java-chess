package chessgame.dao;

import chessgame.domain.Board;
import chessgame.domain.Game;
import chessgame.domain.state.State;

public interface GameDao {
    void save(Board board, String gameName, State turn);

    Game read(String gameName);

    void remove(String gameName);

    String findTurnByGame(String gameName);

}
