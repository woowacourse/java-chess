package chess.dao;

import chess.db.ConnectionManager;
import chess.dto.PieceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class PieceDAO {
    private static final String TABLE_NAME = "pieces";
    private static final String INSERT_TEMPLATE = "INSERT INTO " + TABLE_NAME + " VALUES ";
    private static final String INSERT_QUERY_PLACEHOLDERS = "(?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
    private final ConnectionManager connectionManager;

    public PieceDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void saveAll(List<PieceDTO> pieceDTOs) {
        String query = INSERT_TEMPLATE + getPlaceholders(pieceDTOs.size());
        try (Connection connection = getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(query);
            addPieces(insertStatement, pieceDTOs);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DatabaseException("piece 전체 저장 실패", e);
        }
    }

    private String getPlaceholders(int pieceCount) {
        StringJoiner suffix = new StringJoiner(", ");
        for (int count = 0; count < pieceCount; count++) {
            suffix.add(INSERT_QUERY_PLACEHOLDERS);
        }
        return suffix.toString();
    }

    private void addPieces(PreparedStatement insertStatement, List<PieceDTO> pieceDTOs) throws SQLException {
        int parameterIndex = 1;
        for (PieceDTO pieceDTO : pieceDTOs) {
            insertStatement.setInt(parameterIndex++, pieceDTO.file());
            insertStatement.setInt(parameterIndex++, pieceDTO.rank());
            insertStatement.setString(parameterIndex++, pieceDTO.type());
        }
    }

    public void deleteAll() {
        try (Connection connection = getConnection()) {
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("piece 전체 삭제 실패", e);
        }
    }

    public List<PieceDTO> findAll() {
        try (Connection connection = getConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_QUERY);
            ResultSet selectedPieces = selectStatement.executeQuery();
            return convertToPieces(selectedPieces);
        } catch (SQLException e) {
            throw new DatabaseException("piece 전체 조회 실패", e);
        }
    }

    private List<PieceDTO> convertToPieces(ResultSet selectedPieces) throws SQLException {
        List<PieceDTO> pieces = new ArrayList<>();
        while (selectedPieces.next()) {
            int file = selectedPieces.getInt("piece_file");
            int rank = selectedPieces.getInt("piece_rank");
            String type = selectedPieces.getString("piece_type");
            pieces.add(new PieceDTO(file, rank, type));
        }
        return pieces;
    }

    private Connection getConnection() {
        return connectionManager.getConnection();
    }
}
