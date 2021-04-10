package chess.domain.repository;

import chess.dao.ChessDao;
import chess.dao.dto.ChessGame;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerBundle;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.manager.NotStartedChessGameManager;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ChessGameRepository {
    private final ChessDao chessDao;

    public ChessGameRepository(ChessDao chessDao) {
        this.chessDao = chessDao;
    }

    public ChessGameManager findById(long id) {
        return chessDao.findById(id).map(ChessGameManagerFactory::loadingGame)
                .orElseGet(() -> new NotStartedChessGameManager(id));
    }

    public ChessGameManagerBundle findRunningGames() {
        return chessDao.findAllOnRunning().stream()
                .map(this::createFromEntity)
                .collect(collectingAndThen(toList(), ChessGameManagerBundle::new));
    }

    private ChessGameManager createFromEntity(ChessGame chessGame) {
        return ChessGameManagerFactory.loadingGame(chessGame);
    }

    public long add(ChessGameManager chessGameManager) {
        return chessDao.save(new ChessGame(chessGameManager));
    }

    public void update(ChessGameManager chessGameManager) {
        chessDao.update(new ChessGame(chessGameManager));
    }

    public void delete(long id) {
        chessDao.delete(id);
    }

    public boolean isEnd(long gameId) {
        return findById(gameId).isEnd();
    }
}
