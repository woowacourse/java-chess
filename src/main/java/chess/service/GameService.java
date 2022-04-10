package chess.service;

import chess.dao.GameDao;
import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.domain.state.State;

public class GameService {

    private final GameDao gameDao;

    public GameService(final GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void saveGame(final ChessGame chessGame) {
        final State state = chessGame.getState();
        final String nowState = state.toString();

        gameDao.save(nowState);
    }

    public int findGameId() {
        return gameDao.findGameId();
    }

    public State loadState() {
        try {
            final String state = gameDao.findGameState();
            return State.of(state);
        } catch (NullPointerException e) {
            return new Ready();
        }
    }

    public void update(final ChessGame chessGame) {
        final State state = chessGame.getState();
        final String nowState = state.toString();
        final int gameId = findGameId();

        gameDao.update(nowState, gameId);
    }

    public void delete() {
        gameDao.delete();
    }
}
