package chess.database.dao;

import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addNotation(final Position source, final Position target, final Piece piece) {
        final String query = "INSERT INTO notation(SOURCE, TARGET, TURN) VALUES(?, ?, ?)";
        try (final Connection connection = getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, source.makeToString());
            preparedStatement.setString(2, target.makeToString());
            preparedStatement.setString(3, piece.getTeam().getTeam());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Notation> readNotation() {
        final String query = "SELECT * FROM notation";
        try (final Connection connection = getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();
            return getNotations(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNotation() {
        final String query = "TRUNCATE notation";
        try (final Connection connection = getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Notation> getNotations(final ResultSet result) throws SQLException {
        List<Notation> notations = new ArrayList<>();
        while (result.next()) {
            notations.add(new Notation(
                result.getString("source"),
                result.getString("target"),
                result.getString("turn")));
        }
        return notations;
    }
}
