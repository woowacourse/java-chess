package chess.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessGameComponentDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private static final String TABLE_NAME = "chessboard";
    private final ConnectionGenerator connectionGenerator;

    public ChessGameDao(ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public List<ChessGameComponentDto> findAll() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
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
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Piece findPieceByPosition(Position position) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + TABLE_NAME + " WHERE `file` = ? AND `rank` = ?");
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

    public List<ChessGameComponentDto> findById(int gameId) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + TABLE_NAME + " WHERE game_id = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();

            final List<ChessGameComponentDto> chessBoardComponents = new ArrayList<>();
            while (resultSet.next()) {
                File file = File.convertToFile(resultSet.getString("file"));
                Rank rank = Rank.convertToRank(resultSet.getInt("rank"));
                Type type = Type.convertToType(resultSet.getString("type"));
                Color color = Color.convertToColor(resultSet.getString("color"));
                ChessGameComponentDto chessGameComponentDto
                        = new ChessGameComponentDto(Position.of(file, rank), type.generatePiece(color), gameId);

                chessBoardComponents.add(chessGameComponentDto);
            }
            return chessBoardComponents;
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void save(ChessGameComponentDto chessGameComponentDto) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (`file`,`rank`,`type`,`color`,`game_id`)VALUES (?,?,?,?,?)");
            statement.setString(1, chessGameComponentDto.position().getFileSymbol());
            statement.setInt(2, chessGameComponentDto.position().getRankValue());
            statement.setString(3, chessGameComponentDto.piece().identifyType());
            statement.setString(4, chessGameComponentDto.piece().getColor().name());
            statement.setInt(5, chessGameComponentDto.gameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Position source, Position target) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET file = ?, `rank` = ? WHERE file = ? AND `rank` = ?");
            statement.setString(1, target.getFileSymbol());
            statement.setInt(2, target.getRankValue());
            statement.setString(3, source.getFileSymbol());
            statement.setInt(4, source.getRankValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void remove(Position target) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + TABLE_NAME + " WHERE file = ? AND `rank` = ?");
            statement.setString(1, target.getFileSymbol());
            statement.setInt(2, target.getRankValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
