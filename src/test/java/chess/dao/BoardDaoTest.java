package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @BeforeEach
    void setup() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "delete from board where 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    @DisplayName("position과 piece를 주면 DB에 위치, 팀, 기물을 저장할 수 있다.")
    @Test
    void saveTest() throws Exception {
        BoardDao boardDao = new BoardDao();
        Position position = Position.valueOf("a1");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber = 1;

        boardDao.save(position, piece, gameNumber);

        Connection connection = JdbcConnector.getConnection();
        String sql = "select * from board where position = ? and game_number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, position.getStringValue());
        statement.setInt(2, gameNumber);
        ResultSet resultSet = statement.executeQuery();
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("position")).isEqualTo(position.getStringValue());
        assertThat(resultSet.getString("piece")).isEqualTo(piece.getType());
        assertThat(resultSet.getString("color")).isEqualTo("WHITE");
    }

    @DisplayName("이동할 위치의 데이터를 이전 위치의 데이터로 수정한다.")
    @Test
    void updateByPositionTest() throws Exception {
        BoardDao boardDao = new BoardDao();
        Position source = Position.valueOf("a1");
        Position destination = Position.valueOf("a2");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber = 1;

        boardDao.save(source, piece, gameNumber);
        boardDao.updateByPosition(destination, source, gameNumber);

        Connection connection = JdbcConnector.getConnection();
        String sql = "select * from board where position = ? and game_number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, destination.getStringValue());
        statement.setInt(2, gameNumber);
        ResultSet resultSet = statement.executeQuery();
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("position")).isEqualTo(destination.getStringValue());
        assertThat(resultSet.getString("piece")).isEqualTo(piece.getType());
        assertThat(resultSet.getString("color")).isEqualTo("WHITE");
    }

    @DisplayName("position에 저장된 piece의 정보를 반환한다.")
    @Test
    void findByPositionTest() throws Exception {
        BoardDao boardDao = new BoardDao();
        Position position = Position.valueOf("a1");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber = 1;

        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        statement.setString(2, position.getStringValue());
        statement.setString(3, piece.getColor());
        statement.setString(4, piece.getType());
        statement.executeUpdate();

        Piece result = boardDao.findByPositionAndGameNumber(position, gameNumber);

        assertThat(result).isEqualTo(piece);
    }

    @DisplayName("체스판의 모든 정보로 board를 만들어 반환한다.")
    @Test
    void findAllTest() throws Exception {
        BoardDao boardDao = new BoardDao();
        Position position = Position.valueOf("a1");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber = 1;

        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        statement.setString(2, position.getStringValue());
        statement.setString(3, piece.getColor());
        statement.setString(4, piece.getType());
        statement.executeUpdate();

        Board result = boardDao.findAllByGameNumber(gameNumber);

        assertThat(result.findPieceBy(position).get()).isEqualTo(piece);
    }

    @DisplayName("position에 저장된 piece의 정보를 삭제한다.")
    @Test
    void deleteTest() throws Exception {
        BoardDao boardDao = new BoardDao();
        Position position = Position.valueOf("a1");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber = 1;

        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        statement.setString(2, position.getStringValue());
        statement.setString(3, piece.getColor());
        statement.setString(4, piece.getType());
        statement.executeUpdate();

        boardDao.delete(position, gameNumber);

        assertThat(boardDao.findByPositionAndGameNumber(position, gameNumber)).isNull();
    }

    @DisplayName("DB에 저장된 정보를 모두 제거한다.")
    @Test
    void deleteAllTest() throws Exception {
        BoardDao boardDao = new BoardDao();
        Position position = Position.valueOf("a1");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber = 1;

        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        statement.setString(2, position.getStringValue());
        statement.setString(3, piece.getColor());
        statement.setString(4, piece.getType());
        statement.executeUpdate();

        boardDao.deleteAll();

        assertThat(boardDao.findByPositionAndGameNumber(position, gameNumber)).isNull();
    }

    @DisplayName("특정 게임방의 정보만을 모두 제거한다.")
    @Test
    void deleteAllByGameNumberTest() throws SQLException {
        BoardDao boardDao = new BoardDao();
        Position position = Position.valueOf("a1");
        Color color = Color.WHITE;
        Piece piece = new Knight(color);
        int gameNumber1 = 1;
        int gameNumber2 = 2;

        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber1);
        statement.setString(2, position.getStringValue());
        statement.setString(3, piece.getColor());
        statement.setString(4, piece.getType());
        statement.executeUpdate();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber2);
        statement.setString(2, position.getStringValue());
        statement.setString(3, piece.getColor());
        statement.setString(4, piece.getType());
        statement.executeUpdate();

        boardDao.deleteAllByGameNumber(gameNumber1);

        assertThat(boardDao.findByPositionAndGameNumber(position, gameNumber1)).isNull();
        assertThat(boardDao.findByPositionAndGameNumber(position, gameNumber2)).isNotNull();
    }
}
