package chess.database;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class DBChessBoardDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호


    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(final Position fromPosition,final Position toPosition) {
        File fromMoveFile = fromPosition.getFile();
        Rank fromMoveRank = fromPosition.getRank();
        File toMoveFile = toPosition.getFile();
        Rank toMoveRank = toPosition.getRank();
        var query = "INSERT INTO move_position(fromMoveFile,fromMoveRank,toMoveFile,toMoveRank) VALUES(?,?,?,?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fromMoveFile.name());
            preparedStatement.setString(2, fromMoveRank.name());
            preparedStatement.setString(3, toMoveFile.name());
            preparedStatement.setString(4, toMoveRank.name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Position> select() {
        List<Position> positions = new ArrayList<>();

        var query = "SELECT * FROM move_position ORDER BY seq ASC";
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File fromMoveFile = File.valueOf(resultSet.getString("fromMoveFile"));
                Rank fromMoveRank = Rank.valueOf(resultSet.getString("fromMoveRank"));
                File tomoveFile = File.valueOf(resultSet.getString("toMoveFile"));
                Rank tomoveRank = Rank.valueOf(resultSet.getString("toMoveRank"));
                positions.add(new Position(fromMoveFile, fromMoveRank));
                positions.add(new Position(tomoveFile, tomoveRank));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (positions.isEmpty()) {
            return null;
        }
        return positions;
    }

    @Override
    public void delete() {
        var query = "TRUNCATE move_position";
        try (var connection= getConnection();
        var preparedStatement=connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
