package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.state.Turn;
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
        assertThat(dao.save("test", "start")).isEqualTo(1);
    }

    @Test
    @DisplayName("이름으로 탐색 확인")
    void findByName() {
        assertThat(dao.findByName("test").getTurn()).isEqualTo(Turn.START);
    }
}