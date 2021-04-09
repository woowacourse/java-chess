package chess.service;

import chess.domain.ChessGame;
import chess.domain.dto.GameDto;
import chess.domain.web.Game;
import chess.domain.web.GameHistory;
import chess.repository.GameDao;
import chess.repository.GameHistoryDao;
import chess.util.BoardInitializer;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {
    private final GameDao gameDao;
    private final GameHistoryDao gameHistoryDao;

    public ChessService(GameDao gameDao, GameHistoryDao gameHistoryDao) {
        this.gameDao = gameDao;
        this.gameHistoryDao = gameHistoryDao;
    }

    public int getAddedGameId(Game game) {
        return gameDao.addGame(game);
    }

    public void addGameHistory(GameHistory gameHistory) throws SQLException {
        gameHistoryDao.addGameHistory(gameHistory);
    }

    public List<GameDto> findGamesByUserId(int userId) {
        return gameDao.findRunningGamesByUserId(userId)
            .stream()
            .map(GameDto::new)
            .collect(Collectors.toList());
    }

    public List<GameHistory> findHistoriesByGameId(int gameId) {
        return gameHistoryDao.findAllGameHistoryByGameId(gameId);
    }

    public void updateGameIsEnd(int gameId) throws SQLException {
        gameDao.updateGameIsEnd(gameId);
    }

    public ChessGame reloadAllHistory(int gameId) {
        List<GameHistory> historiesByGameId = findHistoriesByGameId(gameId);
        ChessGame chessGame = new ChessGame();
        chessGame.initBoard(BoardInitializer.init());
        historiesByGameId.forEach(gameHistory -> chessGame.move(gameHistory.getCommand()));
        return chessGame;
    }
}
