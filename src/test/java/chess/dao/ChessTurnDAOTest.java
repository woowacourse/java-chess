package chess.dao;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessTurnDAOTest {
    ChessTurnDao chessTurnDAO;
    Connection connection;
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() throws Exception {
        connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        jdbcTemplate = JdbcTemplate.getInstance(connection);
        chessTurnDAO = ChessTurnDao.getInstance(jdbcTemplate);
    }

    @Test
    void insertTest1() {
        assertDoesNotThrow(() ->
                chessTurnDAO.insertChessTurn(PieceColor.BLACK));
    }

    @Test
    void selectTest1() throws SQLException {
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int gameId = chessTurnDAO.selectMaxGameId();
        assertThat(chessTurnDAO.selectChessTurn(gameId)).isEqualTo(PieceColor.BLACK);

    }

    @Test
    void selectTest2() throws Exception {
        int gameId = chessTurnDAO.selectMaxGameId();
        assertThrows(SQLException.class, () ->
            chessTurnDAO.selectChessTurn(gameId + 100)
        );
    }

    @Test
    void upDateTest() throws SQLException {
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int gameId = chessTurnDAO.selectMaxGameId();
        chessTurnDAO.updateChessTurn(gameId, PieceColor.WHITE);

        assertThat(chessTurnDAO.selectChessTurn(gameId)).isEqualTo(PieceColor.WHITE);
    }

    @Test
    void selectMaxTest() throws Exception {
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);

        int before = chessTurnDAO.selectMaxGameId();
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);

        assertThat(chessTurnDAO.selectMaxGameId()).isEqualTo(before + 3);
    }

    @Test
    public void deleteTest() throws Exception {
        ChessTurnDao chessTurnDAO = ChessTurnDao.getInstance(jdbcTemplate);
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int id = chessTurnDAO.selectMaxGameId();

        assertThat(chessTurnDAO.selectChessTurn(id)).isEqualTo(PieceColor.BLACK);

        chessTurnDAO.deleteChessTurn(id);

        assertThrows(SQLException.class, () -> chessTurnDAO.selectChessTurn(id));
    }

    @Test
    void selectGameIds() throws Exception {
        int beforeGameSize = chessTurnDAO.selectChessGames().size();

        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);

        assertThat(chessTurnDAO.selectChessGames().size()).isEqualTo(beforeGameSize + 3);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
    }
}