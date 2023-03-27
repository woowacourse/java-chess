package chess.repository.dao;

import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.repository.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPieceDao implements PieceDao {

    @Override
    public List<PieceDto> findAllByChessGameId(final int chessGameId) {
        final String sql = "SELECT * FROM piece WHERE chess_game_id = ?";
        final List<PieceDto> pieceDtos = new ArrayList<>();

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, chessGameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String file = resultSet.getString("file");
                final String rank = resultSet.getString("rank");
                final String side = resultSet.getString("side");
                final String type = resultSet.getString("type");
                pieceDtos.add(PieceDto.of(file, rank, side, type));
            }
            return pieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(final int chessGameId, final List<PieceDto> pieceDtos) {
        for (final PieceDto pieceDto : pieceDtos) {
            save(chessGameId, pieceDto);
        }
    }

    @Override
    public void save(final int chessGameId, final PieceDto pieceDto) {
        final String sql = "INSERT INTO piece(chess_game_id, `file`, `rank`, side, type) values(?, ? ,? ,? ,?)";

        final String file = String.valueOf(pieceDto.getFile().index());
        final String rank = String.valueOf(pieceDto.getRank().index());
        final String side = pieceDto.getSide().name();
        final String type = pieceDto.getType().name();

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, chessGameId);
            preparedStatement.setString(2, file);
            preparedStatement.setString(3, rank);
            preparedStatement.setString(4, side);
            preparedStatement.setString(5, type);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void move(final int chessGameId, final Position source, final Position target) {
        delete(chessGameId, target);
        update(chessGameId, source, target);
    }

    @Override
    public void update(final int chessGameId, final Position source, final Position target) {
        final String sql = "UPDATE piece SET `file` = ?, `rank` = ? WHERE chess_game_id = ? AND `file` = ? AND `rank` = ?";

        final String sourceFile = String.valueOf(source.fileIndex());
        final String sourceRank = String.valueOf(source.rankIndex());
        final String targetFile = String.valueOf(target.fileIndex());
        final String targetRank = String.valueOf(target.rankIndex());

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, targetFile);
            preparedStatement.setString(2, targetRank);
            preparedStatement.setInt(3, chessGameId);
            preparedStatement.setString(4, sourceFile);
            preparedStatement.setString(5, sourceRank);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(final int chessGameId, final Position position) {
        final String sql = "DELETE FROM piece WHERE chess_game_id = ? AND `file` = ? AND `rank` = ?";

        final String positionFile = String.valueOf(position.fileIndex());
        final String positionRank = String.valueOf(position.rankIndex());

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(chessGameId));
            preparedStatement.setString(2, positionFile);
            preparedStatement.setString(3, positionRank);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
