package dao;

import dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
    private static final String TABLE_NAME = "piece";
    private static PieceDao instance = null;

    private PieceDao() {
    }

    public static PieceDao getInstance() {
        if (instance == null) {
            instance = new PieceDao();
        }
        return instance;
    }

    Connection getConnection() {
        return DBConnector.getInstance().getConnection();
    }

    public void addPiece(PieceDto pieceDto, int gameId) {
        final String query = String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?)", TABLE_NAME);
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.fileIndex());
            preparedStatement.setInt(2, pieceDto.rankIndex());
            preparedStatement.setString(3, pieceDto.color());
            preparedStatement.setString(4, pieceDto.type());
            preparedStatement.setInt(5, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new DBException(e);
        }
    }

    public void addAll(List<PieceDto> pieceDtos, int gameId) {
        pieceDtos.forEach(pieceDto -> addPiece(pieceDto, gameId));
    }

    public PieceDto findPiece(int fileIndex, int rankIndex, int gameId) {
        final String query = String.format("SELECT * FROM %s WHERE `file` = ? and `rank` = ? and `gameId` = ?", TABLE_NAME);
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, fileIndex);
            preparedStatement.setInt(2, rankIndex);
            preparedStatement.setInt(3, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String pieceColor = resultSet.getString("pieceColor");
                String pieceType = resultSet.getString("pieceType");
                return new PieceDto(fileIndex, rankIndex, pieceColor, pieceType);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        throw new DBException(gameId + " 에 해당하는 기물 정보를 가져오는 중 오류가 발생했습니다.");
    }

    public List<PieceDto> findAllPieces(int gameId) {
        final List<PieceDto> pieces = new ArrayList<>();
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE gameId = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int fileIndex = resultSet.getInt("file");
                int rankIndex = resultSet.getInt("rank");
                String color = resultSet.getString("pieceColor");
                String type = resultSet.getString("pieceType");
                pieces.add(new PieceDto(fileIndex, rankIndex, color, type));
            }
        } catch (final SQLException e) {
            throw new DBException(e);
        }
        return pieces;
    }

    public void removeAllPieces() {
        final String query = "DELETE FROM " + TABLE_NAME;
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int tupleCount() {
        final var query = "SELECT COUNT(*) AS count FROM " + TABLE_NAME;
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        throw new DBException("전체 기물 개수를 가져오는 중 오류가 발생했습니다.");
    }
}
