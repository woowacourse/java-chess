package chess.dao;

import chess.entity.ChessGame;
import chess.entity.Movement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovementDaoTest {

    private MovementDao movementDAO;
    private ChessDao chessDAO;

    @DisplayName("디비 연결 실패시 메모리로 테스트")
    @BeforeEach
    void setUp() {
        try {
            ConnectionProperties connectionProperties = new ConnectionProperties();
            chessDAO = new MariaChessDao(connectionProperties);
            movementDAO = new MariaMovementDao(connectionProperties);
        } catch (Exception e) {
            chessDAO = new InMemoryChessDao();
            movementDAO = new InMemoryMovementDao();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        movementDAO.deleteAll();
        chessDAO.deleteAll();
    }

    @DisplayName("정상 저장 테스트")
    @Test
    void save() throws SQLException {
        //given
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessDAO.save(chessGame);
        Movement movement = new Movement(chessGame.getId(), "a1", "a2");

        //when
        Movement saved = movementDAO.save(movement);

        //then
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void findAllByChessId() throws SQLException {
        //given
        //given
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessDAO.save(chessGame);
        Movement movement1 = new Movement(chessGame.getId(), "a1", "a2");
        Movement movement2 = new Movement(chessGame.getId(), "a2", "a3");
        movementDAO.save(movement1);
        movementDAO.save(movement2);

        //when
        List<Movement> movements = movementDAO.findAllByChessId(chessGame.getId());

        //then
        assertThat(movements).hasSize(2);
    }
}