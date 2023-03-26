package chess.dao;

import chess.domain.ChessGame;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoardDaoTest {
    private final BoardDao boardDao = new BoardDao();

    @BeforeEach
    void setup() {
        boardDao.deleteAllByRoomName("test");
    }

    @AfterAll()
    void deleteAll() {
        boardDao.deleteAllByRoomName("test");
    }

    @DisplayName("DB에 연결할 수 있다.")
    @Test
    void connectionTest() {
        try (Connection connection = boardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("DB에 변경된 체스판을 저장할 수 있다.")
    @Test
    void saveBoardTest() {
        ChessGame chessGame = new ChessGame();
        Square source = Square.getInstanceOf(File.B, Rank.TWO);
        Square target = Square.getInstanceOf(File.B, Rank.THREE);

        chessGame.move(source, target);

        Assertions.assertDoesNotThrow(() -> boardDao.save(chessGame));
    }
}
