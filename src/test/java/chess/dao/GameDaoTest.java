package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.service.ChessGameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameDaoTest {
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao();
    }

//    @AfterEach
//    void deleteGame() {
//        gameDao.deleteAll();
//    }

    @Test
    void saveTest() {
        ChessGameDto dto = new ChessGameDto("ready", "white");
        gameDao.save(dto);
    }

    @Test
    void update() {
        gameDao.update(new ChessGameDto("PLAYING", "WHITE"), 11);
    }

//    @Test
//    void findByIdTest() {
//        ChessGameDto dto = new ChessGameDto("ready", "white");
//        gameDao.save(dto);
//        ChessGameDto findGameDto = gameDao.findById();
//        assertThat(dto.getStatus()).isEqualTo(findGameDto.getStatus());
//    }
}
