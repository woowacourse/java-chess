package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.GameState;
import com.mysql.cj.exceptions.AssertionFailedException;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    private final GameDao gameDao = new GameDaoImpl(new TestConnectionSetup());

    @AfterEach
    void clear() {
        gameDao.deleteAll();
    }

    @DisplayName("게임 저장 테스트")
    @Test
    void save() {
        gameDao.save(1);
    }

    @DisplayName("게임 조회 테스트")
    @Test
    void load() {
        gameDao.save(1);

        Optional<GameState> chessGameOptional = gameDao.load(1);
        GameState actual = chessGameOptional.orElseThrow(() -> new AssertionFailedException("데이터가 없습니다."));

        assertThat(actual).isEqualTo(GameState.READY);
    }

    @DisplayName("게임 정보 업데이트 테스트")
    @Test
    void update() {
        gameDao.save(1);
        gameDao.updateState(1, GameState.WHITE_RUNNING);

        Optional<GameState> chessGameOptional = gameDao.load(1);
        GameState actual = chessGameOptional.orElseThrow(() -> new AssertionFailedException("데이터가 없습니다."));

        assertThat(actual).isEqualTo(GameState.WHITE_RUNNING);
    }

    @DisplayName("게임 삭제 테스트")
    @Test
    void delete() {
        gameDao.delete(1);
    }

    @DisplayName("게임 전체 삭제 테스트")
    @Test
    void deleteAll() {
        gameDao.deleteAll();
    }
}
