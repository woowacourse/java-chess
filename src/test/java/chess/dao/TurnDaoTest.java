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

        turnDao.save(turn);

        Connection connection = JdbcConnector.getConnection();
        String sql = "select * from turn";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("color")).isEqualTo(turn.name());
    }

    @DisplayName("체스 게임 순서를 수정한다.")
    @Test
    void updateTest() throws SQLException {
        String sql = "insert into turn (color) values (?)";
        Connection connection = JdbcConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Color.BLACK.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();

        turnDao.update(Color.WHITE);

        sql = "select * from turn";
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("color")).isEqualTo(Color.WHITE.name());
    }

    @DisplayName("체스 게임 순서를 반환한다.")
    @Test
    void findTest() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into turn (color) values (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Color.WHITE.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();
        Color turn = turnDao.find();

        assertThat(turn).isEqualTo(Color.WHITE);
    }

    @DisplayName("체스 게임 순서 정보를 삭제한다.")
    @Test
    void deleteTest() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        String sql = "insert into turn (color) values (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Color.WHITE.name());
        statement.executeUpdate();

        TurnDao turnDao = new TurnDao();
        turnDao.delete();

        assertThat(turnDao.find()).isNull();
    }
}
