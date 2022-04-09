package chess.service;

import chess.dao.GameDao;
import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Locale;

public class GameService {

    private GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void saveGame(ChessGame chessGame) {
        State state = chessGame.getState();
        String nowState = state.toString().toLowerCase(Locale.ROOT);

        gameDao.save(nowState);
    }

    public int findGameId() {
        return gameDao.findGameId();
    }

    public State loadState() {
        try {
            String state = gameDao.findGameState();
            return State.of(state);
        } catch (NullPointerException e) {
            return new Ready();
        }
    }

    public void update(ChessGame chessGame) {
        State state = chessGame.getState();
        String nowState = state.toString().toLowerCase(Locale.ROOT);

        int gameId = findGameId();

        gameDao.update(nowState, gameId);
    }

    public void delete() {
        gameDao.delete();
    }
}
