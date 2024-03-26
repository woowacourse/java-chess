package chess.repository;

import chess.domain.chessGame.Turn;
import chess.domain.chessGame.generator.SpaceGenerator;
import chess.domain.chessGame.generator.StringSpaceGenerator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardRepositoryImpl implements ChessBoardRepository {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            return null;
        }
    }

    @Override
    public SpaceGenerator findSpaceGenerator() {
        return new StringSpaceGenerator(findInBoard("board_state"));
    }

    @Override
    public Turn findTurn() {
        return TurnConverter.convert(findInBoard("turn"));
    }

    public String findInBoard(String columnLabel) {
        String result = "";
        final var query = "SELECT * FROM board";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(columnLabel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
