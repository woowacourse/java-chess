package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao();
    }

    //connection
    @DisplayName("board 테이블에 대한 connection 생성한다.")
    @Test
    void connection() {
        final Connection connection = boardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    //findAll_by_select_noWhere(R2)
    @DisplayName("board 테이블에서 모든 데이터를 가져온다.")
    @Test
    void select_findAll() {
        final Map<String, String> board = boardDao.findAll();

        assertThat(board).isNotEmpty();
    }

    @DisplayName("board 테이블에 변경된 데이터를 저장한다.")
    @Test
    void update() {
        final Map<String, String> updatedBoard = new HashMap<>();
        updatedBoard.put("a4", "white_pawn");
        updatedBoard.put("a2", "blank");
        updatedBoard.put("b5", "black_pawn");
        updatedBoard.put("b7", "blank");

        Assertions.assertDoesNotThrow(() -> boardDao.update(updatedBoard));
    }

    @DisplayName("position과 그 위치의 piece를 전달하여 board를 업데이트한다.")
    @Test
    void updateWith() {
        Assertions.assertAll(
            () -> boardDao.updatePiecePosition("a2", "white_pawn"),
            () -> boardDao.updatePiecePosition("a4", "blank"),
            () -> boardDao.updatePiecePosition("b7", "black_pawn"),
            () -> boardDao.updatePiecePosition("b5", "blank")
        );
    }
}
