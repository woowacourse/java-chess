package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.DBConnector;

public class PieceDaoTest {

    @BeforeEach
    void initTable() {
        DBConnector dbConnector = new DBConnector();
        String dropSql = "drop table piece";
        String createSql = "create table piece(\n"
                + "    position varchar(10) not null,\n"
                + "    type varchar(10) not null,\n"
                + "    color varchar(10) not null,\n"
                + "    gameID varchar(10) not null,\n"
                + "    primary key(position),\n"
                + "    foreign key(gameID) references chessGame(gameID)\n"
                + ");";

        try (Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(dropSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(createSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("존재하는 게임에 대한 검색은 예외를 반환하지 않는다")
    @Test
    void findByGameID() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("yaho");

        assertThatNoException().isThrownBy(() -> pieceDao.findByGameID("yaho"));
    }

    @DisplayName("존재하지 않는 게임에 대한 검색은 예외를 반환한다")
    @Test
    void findByGameID_NoSuchGame() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("yaho");

        assertThatThrownBy(() -> pieceDao.findByGameID("yaahoo"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("헉.. 저장 안한거 아냐? 그런 게임은 없어!");
    }
}
