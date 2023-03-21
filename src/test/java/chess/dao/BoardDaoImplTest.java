package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.chess.BoardDaoImpl;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardDaoImplTest {

    private static final int BOARD_ID_FOR_TEST = 9999;

    private final BoardDaoImpl chessDaoImpl = new BoardDaoImpl();

    @Test
    @DisplayName("DB 연결이 성공적으로 된다.")
    @Order(1)
    void connection_success() {
        // when & then
        try (final var connection = chessDaoImpl.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("방 번호와, 체스판을 DB에 저장한다.")
    @Order(2)
    void save_success() {
        // given
        Map<String, String> board = new HashMap<>();
        board.put("a1", "p");
        board.put("a2", "P");

        // when & then
        chessDaoImpl.save(BOARD_ID_FOR_TEST, board);
    }

    @Test
    @DisplayName("방 번호를 기준으로 DB에서 체스판을 가져온다.")
    @Order(3)
    void find_success() {
        // when
        Map<String, String> board = chessDaoImpl.findById(BOARD_ID_FOR_TEST);

        // then
        assertThat(board.keySet().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("방 번호를 기준으로 DB에서 체스판을 지운다.")
    @Order(4)
    void delete_success() {
        // when & then
        chessDaoImpl.remove(BOARD_ID_FOR_TEST);
    }
}
