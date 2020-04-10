package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import dao.PiecesDAO;
import domain.commend.State;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesDAOTest {
    PiecesDAO piecesDAO;

    @BeforeEach
    void init() {
        piecesDAO = PiecesDAO.getInstance();
    }

    @Test
    @DisplayName("DB에 들어가는지 테스트")
    void insert() throws SQLException {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        piecesDAO.addPieces(pieces);
        assertThat(piecesDAO.isSave()).isTrue();
    }

    @Test
    @DisplayName("update하는지 테스트")
    void update() throws SQLException {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        State state = State.of(pieces);
        state.start();
        state.move("move b2 b3");
        piecesDAO.updatePieces(state.getPieces());
        assertThat(piecesDAO.readPieces().get("b3")).isEqualTo("p");
    }

    @Test
    @DisplayName("DB를 삭제하는지 테스트")
    void deleteAll() throws SQLException {
        piecesDAO.deleteAll();
        assertThat(piecesDAO.isSave()).isFalse();
    }

    @AfterEach
    void reset() throws SQLException {
        piecesDAO.deleteAll();
    }
}
