package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.dto.ChessGameDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessGameDaoTest {
    @BeforeEach
    void beforeEach(){

    }
    @Test
    void saveChessGame() {
        ChessGameDao chessGameDao = new ChessGameDao();
        ChessGameDto chessGameDto = new ChessGameDto("first Game", "WHITE");
        assertDoesNotThrow(() -> chessGameDao.save(chessGameDto));
    }

    @Test
    void findByName() {
        ChessGameDao chessGameDao = new ChessGameDao();
        ChessGameDto chessGameDto = chessGameDao.findByName("first Game");
        assertThat(chessGameDto.getPlayer()).isEqualTo("WHITE");
    }

    @Test
    void findAllName() {
        ChessGameDao chessGameDao = new ChessGameDao();
        List<String> names = chessGameDao.findAllName();
        assertThat(names.size()).isEqualTo(1);
    }
}
