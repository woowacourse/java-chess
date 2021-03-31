package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesDAOTest {

    private final PiecesDAO piecesDAO = PiecesDAO.instance();

    @Test
    @DisplayName("요청시 마다 같은 인스턴스인지 확인")
    void instance() {
        assertThat(piecesDAO).isSameAs(PiecesDAO.instance());
    }

    @Test
    @DisplayName("연결 테스트")
    void joinPieces() {
        
    }

    @Test
    @DisplayName("0")
    void addPieces() {

    }

    @Test
    @DisplayName("0")
    void updatePiece() {

    }
}