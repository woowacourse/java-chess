package chess.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private final BoardDao boardDao = new BoardDao();

    @BeforeEach
    void deleteAll() {
        boardDao.deleteAll();
    }

    @Test
    @DisplayName("보드 생성 테스트")
    void saveBoardTest() {
        Long savedId = boardDao.saveBoard("WHITE");

        assertThat(savedId).isInstanceOf(Long.class);
    }
}
