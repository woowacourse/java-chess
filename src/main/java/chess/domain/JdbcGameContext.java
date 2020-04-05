package chess.domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.GameDao;
import chess.dao.MoveDao;
import chess.dao.PlayerDao;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public class JdbcGameContext implements GameContext {
    private final GameDao gameDao = new GameDao();
    private final MoveDao moveDao = new MoveDao();
    private final PlayerDao playerDao = new PlayerDao();

    @Override
    public int addGame(final Player white, final Player black) throws SQLException {
        Game gameToAdd = new Game(white, black);
        return gameDao.add(gameToAdd);
    }

    @Override
    public boolean isEmpty() throws SQLException {
        return gameDao.isEmpty();
    }

    @Override
    public Game findGameById(final int id) throws SQLException {
        return gameDao.find(id);
    }

    @Override
    public Map<Position, Piece> findBoardById(final int id) throws SQLException {
        return findGameById(id).getBoard().getBoard();
    }

    @Override
    public void resetGameById(final int id) throws SQLException {
        moveDao.reset(findGameById(id));
    }

    @Override
    public void finishGameById(final int id) throws SQLException {
        Game game = findGameById(id);
        game.finish();
        recordResult(game);
        gameDao.remove(game);
    }

    private void recordResult(final Game game) throws SQLException {
        if (game.winnerSide() == Side.NONE) {
            playerDao.increaseDraw(game.getPlayerId(Side.WHITE));
            playerDao.increaseDraw(game.getPlayerId(Side.BLACK));
        } else {
            playerDao.increaseWin(game.getPlayerId(game.winnerSide()));
            playerDao.increaseLose(game.getPlayerId(game.winnerSide().opposite()));
        }
    }

    @Override
    public double getScoreById(final int id, final Side side) throws SQLException {
        return gameDao.getScore(findGameById(id), side);
    }

    @Override
    public Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException {
        return gameDao.getPlayerContexts();
    }

    @Override
    public Map<Integer, Map<Side, Double>> getScoreContexts() throws SQLException {
        return gameDao.getScoreContexts();
    }

    @Override
    public Map<Side, Double> getScoresById(final int id) throws SQLException {
        Map<Side, Double> scores = new HashMap<>();
        scores.put(Side.WHITE, getScoreById(id, Side.WHITE));
        scores.put(Side.BLACK, getScoreById(id, Side.BLACK));
        return scores;
    }

    @Override
    public void addMoveByGameId(final int id, final MoveRequestDto dto) throws SQLException {
        moveDao.addMove(findGameById(id), dto);
        gameDao.updateScores(findGameById(id));
    }
}
