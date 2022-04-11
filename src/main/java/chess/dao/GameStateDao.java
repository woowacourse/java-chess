package chess.dao;

public interface GameStateDao {

    boolean hasPlayingGame();

    void saveState(final String state);

    void saveTurn(final String turn);

    String getGameState();

    String getTurn();

    void removeGameState();
}
