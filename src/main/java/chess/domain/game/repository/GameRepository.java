package chess.domain.game.repository;

import java.util.List;
import java.util.Map;

import chess.dao.connect.JdbcTemplate;
import chess.dao.dto.GameDto;
import chess.dao.dto.GameUpdateDto;
import chess.dao.mysql.GameDao;
import chess.domain.Color;
import chess.domain.game.ChessGame;
import chess.domain.player.Player;
import chess.domain.player.Players;
import chess.domain.player.repository.PlayerRepository;

public class GameRepository {

    private final PlayerRepository playerRepository;
    private final GameDao gameDao;

    public GameRepository(final PlayerRepository playerRepository, final JdbcTemplate jdbcTemplate) {
        this(playerRepository, new GameDao(jdbcTemplate));
    }

    public GameRepository(final PlayerRepository playerRepository, final GameDao gameDao) {
        this.playerRepository = playerRepository;
        this.gameDao = gameDao;
    }

    public ChessGame save(final ChessGame chessGame) {
        final List<Player> players = playerRepository.savePlayers(chessGame.getPlayers());
        final Color currentTurnColor = chessGame.getColorOfCurrentTurn();
        final boolean finished = chessGame.isFinished();
        final GameDto gameDto = new GameDto(
                0L, players.get(0).getId(), players.get(1).getId(), finished, currentTurnColor.getName());
        return ChessGame.loadChessGame(gameDao.save(gameDto), new Players(players), finished, currentTurnColor);
    }

    public ChessGame findById(final Long id) {
        final GameDto gameDto = gameDao.findById(id);
        final Players players = new Players(
                playerRepository.findById(gameDto.getPlayer_id1()),
                playerRepository.findById(gameDto.getPlayer_id2()));
        final boolean finished = gameDto.getFinished();
        final Color currentTurnColor = Color.from(gameDto.getCurrentTurnColor());

        return ChessGame.loadChessGame(id, players, finished, currentTurnColor);
    }

    public Map<Long, Boolean> findIdAndFinished() {
        return gameDao.findIdAndFinished();
    }

    public ChessGame update(final ChessGame chessGame) {
        final Long gameId = chessGame.getId();
        final boolean finished = chessGame.isFinished();
        final Color currentTurnColor = chessGame.getColorOfCurrentTurn();
        gameDao.update(new GameUpdateDto(gameId, finished, currentTurnColor.getName()));

        final Players players = chessGame.getPlayers();
        for (final Player player : players.getPlayers()) {
            playerRepository.update(player);
        }
        return findById(gameId);
    }

    public void remove(final long id) {
        final Players players = findById(id).getPlayers();
        for (final Player player : players.getPlayers()) {
            playerRepository.remove(player.getId());
        }
        gameDao.remove(id);
    }
}
