package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.GameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    @Test
    @DisplayName("데이터베이스에 게임 정보를 저장한다.")
    void save() {
        GameDao gameDao = new GameDao();
        gameDao.save(new GameDto("Started", "black"));
    }

    @Test
    @DisplayName("데이터베이스에서 아이디를 이용해 게임 정보를 불러온다.")
    void findById() {
        GameDao gameDao = new GameDao();
        GameDto game = gameDao.findById(1);

        assertThat(game.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("데이터베이스에서 게임의 상태, 턴 정보를 업데이트한다.")
    void updateById() {
        GameDao gameDao = new GameDao();
        GameDto gameDto = new GameDto(1, "Started", "white");
        gameDao.updateById(gameDto);
    }

    @Test
    @DisplayName("데이터베이스에서 가장 최근에 저장 된 게임 정보를 불러온다.")
    void findByLatestDate() {
        GameDao gameDao = new GameDao();
        GameDto game = gameDao.findByMaxId();

        assertThat(game.getTurn()).isEqualTo("black");
    }
}
