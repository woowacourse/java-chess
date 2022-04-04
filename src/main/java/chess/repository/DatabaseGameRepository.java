package chess.repository;

import chess.dao.GameDao;
import chess.domain.ChessGame;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseGameRepository implements GameRepository {
    private final GameDao gameDao;

    public DatabaseGameRepository(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public void save(ChessGame game) {
        gameDao.save(game);
    }

    @Override
    public Optional<ChessGame> findById(Long id) {
        return Optional.of(gameDao.findById(id));
    }

    @Override
    public List<ChessGame> findAll() {
        return gameDao.findAll();
    }

    @Override
    public List<ChessGame> findHistorysByMemberId(Long memberId) {
        return gameDao.findAll()
                .stream()
                .filter(ChessGame::isEnd)
                .filter(game -> Objects.equals(game.getParticipant().getBlackId(), memberId)
                        || Objects.equals(game.getParticipant().getWhiteId(), memberId))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ChessGame game) {
        gameDao.update(game);
    }
}
