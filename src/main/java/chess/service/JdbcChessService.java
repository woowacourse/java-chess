package chess.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.dao.GameDao;
import chess.dao.MoveDao;
import chess.dao.PlayerDao;
import chess.domain.Game;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public class JdbcChessService implements ChessService {
    private final GameDao gameDao = new GameDao();
    private final MoveDao moveDao = new MoveDao();
    private final PlayerDao playerDao = new PlayerDao();

    @Override
    public Game findGameById(final int id) throws SQLException {
        Game game = new Game(id);
        List<MoveRequestDto> moves = moveDao.getMoves(game);
        Map<Side, Player> players = playerDao.getPlayersByGameId(id);
        game.setPlayer(Side.WHITE, players.get(Side.WHITE));
        game.setPlayer(Side.BLACK, players.get(Side.BLACK));
        moves.forEach(move -> game.move(move.getFrom(), move.getTo()));
        gameDao.updateScores(game);
        return game;
    }

    @Override
    public Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException {
        return playerDao.getPlayerContexts();
    }

    @Override
    public Map<Position, Piece> findBoardById(int id) throws SQLException {
        return findGameById(id).getBoard().getBoard();
    }

    @Override
    public Map<Position, Piece> resetGameById(int id) throws SQLException {
        moveDao.reset(findGameById(id));
        return findBoardById(id);
    }

    @Override
    public Map<Integer, Map<Side, Player>> addGame(Player white, Player black) throws SQLException {
        HashMap<Integer, Map<Side, Player>> result = new HashMap<>();
        Game gameToAdd = new Game(white, black);
        int gameId = gameDao.add(gameToAdd);
        result.put(gameId, findGameById(gameId).getPlayers());
        return result;
    }

    @Override
    public List<String> findAllAvailablePath(int id, String from) throws SQLException {
        return findGameById(id).findAllAvailablePath(from);
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
    public double getScoreById(final int id, final Side side) throws SQLException {
        return gameDao.getScore(findGameById(id), side);
    }

    @Override
    public boolean isWhiteTurn(final int id) throws SQLException {
        return findGameById(id).isWhiteTurn();
    }

    @Override
    public boolean addMoveByGameId(final int id, MoveRequestDto dto) throws SQLException {
        boolean movable = findGameById(id).move(dto.getFrom(), dto.getTo());
        if (movable) {
            moveDao.addMove(findGameById(id), dto);
            gameDao.updateScores(findGameById(id));
        }
        return movable;
    }

    @Override
    public boolean finishGameById(final int id) throws SQLException {
        Game game = findGameById(id);
        game.finish();
        recordResult(game);
        moveDao.reset(game);
        gameDao.remove(game);
        return true;
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
    public boolean isGameOver(final int id) throws SQLException {
        return findGameById(id).isGameOver();
    }
}
