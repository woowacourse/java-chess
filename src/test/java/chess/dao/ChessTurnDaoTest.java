package chess.dao;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessTurnDaoTest {
    ChessTurnDao chessTurnDAO;
    Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = DBUtil.getConnection();
        connection.setAutoCommit(false);
        chessTurnDAO = ChessTurnDao.getInstance();
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
        assertThat(chessTurnDAO.selectChessTurn(gameId + 100)).isNull();
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
        ChessTurnDao chessTurnDAO = ChessTurnDao.getInstance();
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int id = chessTurnDAO.selectMaxGameId();

        assertThat(chessTurnDAO.selectChessTurn(id)).isEqualTo(PieceColor.BLACK);

        chessTurnDAO.deleteChessTurn(id);

        assertThat(chessTurnDAO.selectChessTurn(id)).isNull();
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
        connection.close();
    }
}