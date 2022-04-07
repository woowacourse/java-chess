package web.dao;

import chess.domain.piece.Color;
import web.dto.GameInfoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameDaoTest {

    private static final String ROOM_NAME = "게임1";
    private GameDao gameDao;

    @BeforeEach
    void beforeEach() {
        gameDao = new GameDao();
    }

    @Test
    @DisplayName("game정보를 올바르게 저장한다.")
    void save() {
        GameInfoDto gameDto = new GameInfoDto(ROOM_NAME);

        assertThatCode(() -> gameDao.save(gameDto))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복된 게임 이름이 존재하면 예외를 발생시킨다.")
    void throwsExceptionWithDuplicateName() {
        GameInfoDto gameDto = new GameInfoDto(ROOM_NAME);
        gameDao.save(gameDto);

        assertThatThrownBy(() -> gameDao.save(gameDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 중복된 이름의 게임이 존재합니다.");
    }

    @Test
    @DisplayName("db에서 방 이름을 통해 올바른 데이터를 가져온다.")
    void findByRoomName() {
        gameDao.save(new GameInfoDto(ROOM_NAME));

        GameInfoDto game = gameDao.findByRoomName(ROOM_NAME);
        assertThat(game.getRoomName()).isEqualTo(ROOM_NAME);
    }

    @Test
    @DisplayName("turn color를 수정한다.")
    void update() {
        gameDao.save(new GameInfoDto(ROOM_NAME));

        gameDao.update(new GameInfoDto(ROOM_NAME, Color.BLACK.toString()));
        GameInfoDto game = gameDao.findByRoomName(ROOM_NAME);
        assertThat(game.getTurnColor()).isEqualTo(Color.BLACK.toString());
    }

    @AfterEach
    void afterEach() {
        gameDao.delete(ROOM_NAME);
    }
}
