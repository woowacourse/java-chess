package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MoveHistoryDto;

public class JdbcChessDao implements ChessDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
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

    @Override
    public void addGame(String gameName) {
        final String query = "INSERT INTO game (gameName) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<String> findAllGame() {
        final String query = "SELECT gameName FROM game";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> gameNames = new ArrayList<>();
            while (resultSet.next()) {
                String gameName = resultSet.getString("gameName");
                gameNames.add(gameName);
            }
            return gameNames;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void findBoardByGameName() {}

    public long findGameIdByGameName(String gameName) {
        final String query = "SELECT _id FROM game WHERE gameName = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("_id");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteAllGame() {
        final String query = "DELETE from game";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveBoard() {

    }

    @Override
    public void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto) {
        final String query = "INSERT INTO moveHistory (source, target, pieceOnTarget, g_id) VALUES (?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveHistoryDto.getSource());
            preparedStatement.setString(2, moveHistoryDto.getTarget());
            preparedStatement.setString(3, moveHistoryDto.getPiece());
            preparedStatement.setLong(4, game_id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<MoveHistoryDto> findMoveHistoryByGameId(long game_id) {
        final String query = "SELECT source, target, pieceOnTarget FROM moveHistory WHERE g_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, game_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MoveHistoryDto> moveHistoryDtos = new ArrayList<>();
            while (resultSet.next()) {
                String source = resultSet.getString("source");
                String target = resultSet.getString("target");
                String piece = resultSet.getString("pieceOnTarget");
                moveHistoryDtos.add(new MoveHistoryDto(source, target, piece));
            }
            return moveHistoryDtos;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
