package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.dto.ColorDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private ChessGameDao dao;

    @BeforeEach
    void init() {
        dao = new ChessGameDao();
    }

    @Test
    @DisplayName("저장 확인")
    void save() {
        assertThat(dao.save("test", "white")).isEqualTo(1);
    }

    @Test
    @DisplayName("이름으로 탐색 확인")
    void findByName() {
        dao.save("test", "white");
        ColorDto result = dao.findByName("test").orElse(ColorDto.from(Color.EMPTY));
        assertThat(result.getValue()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("turn 업데이트 구현")
    void updateTurn() {
        dao.save("test", "white");
        dao.updateTurn("black", "test");
        ColorDto result = dao.findByName("test").orElse(ColorDto.from(Color.EMPTY));
        assertThat(result.getValue()).isEqualTo(Color.BLACK);
    }

    @AfterEach
    void delete(){
        dao.deleteByName("test");
    }
}