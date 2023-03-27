package chess.service;

import chess.dao.GameDao;
import chess.dao.JdbcTemplate;
import chess.domain.ChessGame;

public class GameService {

    private final GameDao gameDao = new GameDao(new JdbcTemplate());

    public int saveGame(final ChessGame chessGame) {
        return gameDao.saveGame(chessGame);
    }

    public void updateGame(final ChessGame chessGame, final int gameId) {
        gameDao.updateGameById(chessGame, gameId);
    }

    public ChessGame findGame(final int gameId) {
        return gameDao.findGameById(gameId);
    }
}
