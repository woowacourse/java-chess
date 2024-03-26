package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.PieceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBServiceTest {
    private PiecesDao piecesDao;

    @BeforeEach
    void setUp() {
        piecesDao = DBFixtures.createPiecesDao();
    }

    @AfterEach
    void tearDown() {
        piecesDao.deleteAll();
    }

    @Test
    @DisplayName("기존에 저장된 데이터가 있는지 확인한다.")
    void hasPreciousData() {
        DBService dbService = DBFixtures.createDBService();
        piecesDao.create(new PieceDto(1, 1, "WHITE_ROOK"));

        assertThat(dbService.hasPreviousData()).isTrue();
    }

    @Test
    @DisplayName("기존에 저장된 데이터가 없는지 확인한다.")
    void hasNotPreviousData() {
        DBService dbService = DBFixtures.createDBService();

        assertThat(dbService.hasPreviousData()).isFalse();
    }
}
