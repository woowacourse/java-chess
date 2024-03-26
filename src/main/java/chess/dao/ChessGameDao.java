package chess.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessGameComponentDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    private static final String APP_TABLE_NAME = "chessboard";
    private static final String TEST_TABLE_NAME = "chessboard_for_test";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<ChessGameComponentDto> findAll() {
        String tableName = getTableName();
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName);
            final ResultSet resultSet = statement.executeQuery();

            final List<ChessGameComponentDto> chessBoardComponents = new ArrayList<>();
            while (resultSet.next()) {
                File file = File.convertToFile(resultSet.getString("file"));
                Rank rank = Rank.convertToRank(resultSet.getInt("rank"));
                Type type = Type.convertToType(resultSet.getString("type"));
                Color color = Color.convertToColor(resultSet.getString("color"));
                int gameId = resultSet.getInt("game_id");
                ChessGameComponentDto chessGameComponentDto
                        = new ChessGameComponentDto(Position.of(file, rank), type.generatePiece(color), gameId);

                chessBoardComponents.add(chessGameComponentDto);
            }
            return chessBoardComponents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Piece findPieceByPosition(Position position) {
        try (final var connection = getConnection()) {
            String tableName = getTableName();
            final var statement = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE `file` = ? AND `rank` = ?");
            statement.setString(1, position.getFileSymbol());
            statement.setInt(2, position.getRankValue());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Type type = Type.convertToType(resultSet.getString("type"));
                Color color = Color.convertToColor(resultSet.getString("color"));

                return type.generatePiece(color);
            }
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void save(ChessGameComponentDto chessGameComponentDto) {
        String tableName = getTableName();
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement(
                    "INSERT INTO " + tableName + " (`file`,`rank`,`type`,`color`,`game_id`)VALUES (?,?,?,?,?)");
            statement.setString(1, chessGameComponentDto.position().getFileSymbol());
            statement.setInt(2, chessGameComponentDto.position().getRankValue());
            statement.setString(3, chessGameComponentDto.piece().identifyType());
            statement.setString(4, chessGameComponentDto.piece().getColor().name());
            statement.setInt(5, chessGameComponentDto.gameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Position source, Position target) {
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement(
                    "UPDATE " + getTableName() + " SET file = ?, `rank` = ? WHERE file = ? AND `rank` = ?");
            statement.setString(1, target.getFileSymbol());
            statement.setInt(2, target.getRankValue());
            statement.setString(3, source.getFileSymbol());
            statement.setInt(4, source.getRankValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Position target) {
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + getTableName() + " WHERE file = ? AND `rank` = ?");
            statement.setString(1, target.getFileSymbol());
            statement.setInt(2, target.getRankValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTableName() {
        if (isTestEnvironment()) {
            return TEST_TABLE_NAME;
        }
        return APP_TABLE_NAME;
    }

    private boolean isTestEnvironment() {
        String testStatus = System.getProperty("TEST_ENV");
        return testStatus != null && testStatus.equalsIgnoreCase("true");
    }
}
