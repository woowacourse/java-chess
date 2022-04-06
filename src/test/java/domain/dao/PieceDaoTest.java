package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.dto.PieceDto;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    @Test
    void setup() {
        PieceDao pieceDao = new PieceDao();
        Connection connection = pieceDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void savePiece() {
        PieceDao pieceDao = new PieceDao();
        PieceDto pieceDto = new PieceDto("first Game", "a2", "p", "WHITE");
        assertDoesNotThrow(() -> pieceDao.save(pieceDto));
    }

    @Test
    void findByGameName(){
        PieceDao pieceDao = new PieceDao();
        List<PieceDto> pieces = pieceDao.findByGameName("mygame");
        assertThat(pieces.size()).isEqualTo(64);
    }
}
