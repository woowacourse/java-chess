package chess.dao;

public interface GameDao {

    int saveAndGetGeneratedId();

    void finishGame(int gameId);

    boolean checkById(int gameId);

    int countAll();

    int countByState(GameState state);
}
