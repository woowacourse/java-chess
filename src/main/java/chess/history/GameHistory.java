package chess.history;

import database.GameDAO;
import java.util.List;

public final class GameHistory {
    
    private static final GameDAO GAME_DAO = new GameDAO("game");
    private final List<Integer> games;
    
    private GameHistory(final List<Integer> games) {
        this.games = games;
    }
    
    public static GameHistory create() {
        List<Integer> games = GAME_DAO.fetchGames();
        return new GameHistory(games);
    }
    
    public void add() {
        GAME_DAO.addGame();
    }
    
    public void reset(final int gameID) {
        GAME_DAO.resetGame(gameID);
    }
    
    public int getLastGameID() {
        return GAME_DAO.getLastGameID();
    }
    
    public boolean isEmpty() {
        return this.games.isEmpty();
    }
    
    public List<Integer> getGameNumbers() {
        return this.games;
    }
}
