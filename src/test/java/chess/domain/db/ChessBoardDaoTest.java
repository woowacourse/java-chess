package chess.domain.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

class ChessBoardDaoTest {

    private final ChessBoardDao chessBoardDao = new ChessBoardDao();

    @Test
    public void connection() {
        try (final var connection = chessBoardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addPiece() {
        final var piece = new ChessBoard("a1", "WHITE", "KING");
        chessBoardDao.addPiece(piece);
    }

    @Test
    public void findByPosition() {
        final var piece = chessBoardDao.findByPosition("a1");

        assertThat(piece).isEqualTo(new ChessBoard("a1", "WHITE", "KING"));
    }

    @Test
    public void findAll() {
        final var piece = chessBoardDao.findAll();

        assertThat(piece).isEqualTo(List.of(new ChessBoard("a1", "WHITE", "KING")));
    }

    @Test
    public void deleteAll() {
        chessBoardDao.deleteAll();

        assertThat(chessBoardDao.findByPosition("a1")).isNull();
    }
}
