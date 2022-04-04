package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PiecesDao 는")
class PiecesDaoTest {

    @Test
    @DisplayName("디비에 연결되야 한다.")
    void connection() {
        final PiecesDao piecesDao = new PiecesDao();
        final Connection connection = piecesDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("말들의 정보를 가져올 수 있어야 한다.")
    void get_Pieces() {
        final PiecesDao piecesDao = new PiecesDao();
        assertThatNoException().isThrownBy(() -> piecesDao.getPieces(2));
    }

    @Test
    @DisplayName("말의 위치를 이동시킬 수 있어야 한다.")
    void change_Position() {
        final PiecesDao piecesDao = new PiecesDao();
        assertThatNoException().isThrownBy(
                () -> piecesDao.changePosition("a2", "a4", 2)
        );
    }

    @Test
    @DisplayName("잡힌 말을 지울 수 있어야 한다.")
    void delete_Piece() {
        final PiecesDao piecesDao = new PiecesDao();
        assertThatNoException().isThrownBy(
                () -> piecesDao.deletePiece("a2")
        );
    }
}
