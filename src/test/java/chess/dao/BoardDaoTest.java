package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.board.BoardDao;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardDaoTest {

    private static final int BOARD_ID_FOR_TEST = 9999;
    private static final String positions = "a1a2a3";
    private static final String pieces = "pkP";

    private final BoardDao boardDao = new BoardDao();

    @Test
    @DisplayName("DB 연결이 성공적으로 된다.")
    @Order(1)
    void connection_success() {
        // when & then
        try (final var connection = boardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("방 번호와, 체스판을 DB에 저장한다.")
    @Order(2)
    void save_success() {
        // when & then
        boardDao.save(BOARD_ID_FOR_TEST, positions, pieces, true);
    }

    @Test
    @DisplayName("방 번호를 기준으로 DB에서 체스판을 가져온다.")
    @Order(3)
    void find_success() {
        // when
        List<String> positionsWithPieces = boardDao.findById(BOARD_ID_FOR_TEST);
        String positionsGot = positionsWithPieces.get(0);
        String pieceGot = positionsWithPieces.get(1);

        // then
        assertAll(
                () -> assertThat(positionsGot).isEqualTo(positions),
                () -> assertThat(pieceGot).isEqualTo(pieces)
        );
    }

    @Test
    @DisplayName("방 번호를 기준으로 체스판의 턴을 가져온다.")
    @Order(4)
    void find_turn() {
        // when
        boolean isLowerTeamTurn = boardDao.isLowerTeamTurnByBoardId(BOARD_ID_FOR_TEST);

        // then
        assertThat(isLowerTeamTurn).isTrue();
    }

    @Test
    @DisplayName("방 번호를 기준으로 DB에서 체스판을 지운다.")
    @Order(5)
    void delete_success() {
        // when & then
        boardDao.remove(BOARD_ID_FOR_TEST);
    }
}
