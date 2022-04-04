package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.dto.PieceDto;
import chess.utils.DatabaseUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class PieceDaoImplTest {

    private static final Connection connection = DatabaseUtil.getConnection();
    private static final PieceDao pieceDao = new PieceDaoImpl(connection);

    @AfterAll
    static void close() {
        DatabaseUtil.closeConnection(connection);
    }

    @Test
    void create() {
        List<PieceDto> pieces = new ArrayList<>();
        pieces.add(new PieceDto("WHITE", "rook", "a2"));
        pieces.add(new PieceDto("BLACK", "rook", "a3"));
        pieces.add(new PieceDto("BLACK", "bishop", "a4"));
        assertThatNoException().isThrownBy(() -> pieceDao.create(pieces, 1));
    }

    @Test
    void updatePosition() {
        assertThatNoException().isThrownBy(() -> pieceDao.updatePosition("a2", "a4"));
    }

    @Test
    void findByBoardId() {
        assertThat(pieceDao.findByBoardId(1))
                .contains(new PieceDto("BLACK", "rook", "a3"));
    }

    @Test
    void delete() {
        assertThatNoException().isThrownBy(() -> pieceDao.delete("a4"));
    }
}