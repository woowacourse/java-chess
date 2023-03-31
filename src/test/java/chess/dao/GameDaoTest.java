package chess.dao;

import chess.domain.Color;
import chess.dto.GameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class GameDaoTest {
    @BeforeEach
    void setUp() {
        new MovementDao().deleteAll();
        new GameDao().deleteAll();
    }

    @DisplayName("게임 정보(isEnd, lastPlayer)을 저장할 때 반환된 gameId를 이용한 조회에 성공한다.")
    @Test
    void findBy() {
        // given
        final GameDao gameDao = new GameDao();

        // when
        final long savedGameId = gameDao.save(false, Color.BLACK);
        final Optional<GameDto> maybeGameMapper = gameDao.findBy(savedGameId);

        // then
        assertThat(maybeGameMapper).isPresent();
    }

    @DisplayName("게임 정보(isEnd, lastPlayer) 전체 조회에 성공한다.")
    @Test
    void findAll() {
        // given
        final GameDao gameDao = new GameDao();

        // when
        final long savedGameId1 = gameDao.save(false, Color.BLACK);
        final long savedGameId2 = gameDao.save(false, Color.WHITE);
        final long savedGameId3 = gameDao.save(true, Color.BLACK);

        final List<GameDto> findAllGameDtos = gameDao.findLatestGamesWithoutBy(0L);
        final List<Long> gameIds = findAllGameDtos.stream().map(GameDto::getGameId).collect(Collectors.toList());

        // then
        assertThat(gameIds).contains(savedGameId1, savedGameId2, savedGameId3);
    }

    @DisplayName("게임 정보(isEnd, lastPlayer)을 저장한다.")
    @Test
    void save() {
        // given
        final GameDao gameDao = new GameDao();

        // when
        final long savedGameId = gameDao.save(false, Color.BLACK);

        // then
        assertThat(savedGameId).isGreaterThan(0L);
    }

    @DisplayName("게임 정보를 삭제한다.")
    @Test
    void deleteBy() {
        // given
        final GameDao gameDao = new GameDao();

        // when
        final long savedGameId = gameDao.save(false, Color.BLACK);
        final boolean isDeleted = gameDao.deleteBy(savedGameId);

        // then
        assertThat(isDeleted).isTrue();
    }
}
