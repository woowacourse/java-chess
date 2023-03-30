package dao;

import dto.PieceDto;
import dto.PositionDto;
import util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPieceDao implements PieceDao {

    @Override
    public void save(List<PieceDto> pieceDtos) {
        for (PieceDto pieceDto : pieceDtos) {
            final var query = "INSERT INTO piece_state(piece_name, piece_color, piece_row, piece_column) VALUES (?, ?, ?, ?)";
            try (final var connection = DBUtil.getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pieceDto.getName());
                preparedStatement.setString(2, pieceDto.getPieceColor());
                preparedStatement.setInt(3, pieceDto.getRow());
                preparedStatement.setInt(4, pieceDto.getColumn());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void save(PieceDto pieceDto) {
        final var query = "INSERT INTO piece_state(piece_name, piece_color, piece_row, piece_column) VALUES (?, ?, ?, ?)";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceDto.getName());
            preparedStatement.setString(2, pieceDto.getPieceColor());
            preparedStatement.setInt(3, pieceDto.getRow());
            preparedStatement.setInt(4, pieceDto.getColumn());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PieceDto> findAllPieces() {
        List<PieceDto> pieceDtos = new ArrayList<>();

        final var query = "SELECT piece_name, piece_color, piece_row, piece_column FROM piece_state";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pieceName = resultSet.getString("piece_name");
                String pieceColor = resultSet.getString("piece_color");
                int pieceRow = resultSet.getInt("piece_row");
                int pieceColumn = resultSet.getInt("piece_column");

                pieceDtos.add(new PieceDto(pieceName, pieceColor, pieceRow, pieceColumn));
            }
            return pieceDtos;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePiece(PositionDto beforePositionDto, PositionDto afterPositionDto, PieceDto pieceDto) {
        deleteByPosition(beforePositionDto);
        deleteByPosition(afterPositionDto);
        save(pieceDto);
    }

    @Override
    public void deleteByPosition(PositionDto positionDto) {
        final var query = "DELETE FROM piece_state WHERE piece_row = ? AND piece_column = ?";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, positionDto.getRow());
            preparedStatement.setInt(2, positionDto.getColumn());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllPieces() {
        final var query = "DELETE FROM piece_state";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
