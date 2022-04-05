package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurnDaoTest {

    @BeforeEach
    void setup() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "delete from turn where 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    @DisplayName("체스 게임 순서를 저장한다.")
    @Test
    void saveTest() throws SQLException {
        TurnDao turnDao = new TurnDao();
        State state = Ready.start(new Board(new HashMap<>()));
        Color turn = state.getTurn();
        int gameNumber = 1;

        turnDao.save(turn, gameNumber);

        Connection connection = JdbcConnector.getConnection();
        String sql = "select * from turn where game_number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        ResultSet resultSet = statement.executeQuery();
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("color")).isEqualTo(turn.name());
    }

    @DisplayName("체스 게임 순서를 수정한다.")
    @Test
    void updateTest() throws SQLException {
        int gameNumber = 1;
        String sql = "insert into turn (game_number, color) values (?,?)";
        Connection connection = JdbcConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        statement.setString(2, Color.BLACK.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();

        turnDao.update(Color.WHITE, gameNumber);

        sql = "select * from turn where game_number = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, gameNumber);
        ResultSet resultSet = statement.executeQuery();
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("color")).isEqualTo(Color.WHITE.name());
    }

    @DisplayName("체스 게임 순서를 반환한다.")
    @Test
    void findTest() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into turn (game_number, color) values (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        int gameNumber = 1;
        statement.setInt(1, gameNumber);
        statement.setString(2, Color.WHITE.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();
        Color turn = turnDao.findByGameNumber(gameNumber);

        assertThat(turn).isEqualTo(Color.WHITE);
    }

    @DisplayName("체스 게임 순서 정보를 삭제한다.")
    @Test
    void deleteTest() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into turn (game_number, color) values (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        int gameNumber = 1;
        statement.setInt(1, gameNumber);
        statement.setString(2, Color.WHITE.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();
        turnDao.delete();

        assertThat(turnDao.findByGameNumber(gameNumber)).isNull();
    }

    @DisplayName("특정 게임방의 체스 게임 순서 정보를 제거한다.")
    @Test
    void deleteByGameNumber() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into turn (game_number, color) values (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        int gameNumber1 = 1;
        int gameNumber2 = 2;
        statement.setInt(1, gameNumber1);
        statement.setString(2, Color.WHITE.name());
        statement.executeUpdate();
        statement.setInt(1, gameNumber2);
        statement.setString(2, Color.WHITE.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();
        turnDao.deleteByGameNumber(gameNumber1);

        assertThat(turnDao.findByGameNumber(gameNumber1)).isNull();
        assertThat(turnDao.findByGameNumber(gameNumber2)).isNotNull();
    }
}
