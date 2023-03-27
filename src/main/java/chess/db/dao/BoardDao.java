package chess.db.dao;

import chess.db.DBConnection;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDao {

    public void insert(final Map<Square, Piece> pieces) {
        final String query = "INSERT INTO board (piece_type, piece_team, piece_file, piece_rank) VALUES(?, ?, ?, ?)";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Square square : pieces.keySet()) {
                preparedStatement.setString(1, pieces.get(square).getPieceType().name());
                preparedStatement.setString(2, pieces.get(square).getTeam().name());
                preparedStatement.setString(3, square.getFile().getFile());
                preparedStatement.setString(4, square.getRank().getRank());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Square, Piece> select() {
        final String query = "SELECT * FROM board";
        final Map<Square, Piece> pieces = new LinkedHashMap<>();
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = File.findFileBy(resultSet.getString("piece_file"));
                Rank rank = Rank.findRankBy(resultSet.getString("piece_rank"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                Team team = Team.valueOf(resultSet.getString("piece_team"));

                pieces.put(Square.of(file, rank), pieceType.getPiece(team));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (pieces.isEmpty()) {
            return null;
        }
        return pieces;
    }

    public int findBySquare(final Square square) {
        final String query = "SELECT * FROM board where piece_file = ? AND piece_rank = ?";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.getFile().getFile());
            preparedStatement.setString(2, square.getRank().getRank());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void update(final Square source, final Square destination) {
        int pieceId = findBySquare(source);
        if (pieceId == 0) {
            throw new RuntimeException("해당 위치에 말이 없습니다.");
        }
        final String query = "UPDATE board SET piece_file = ?, piece_rank = ? WHERE id = ?";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, destination.getFile().getFile());
            preparedStatement.setString(2, destination.getRank().getRank());
            preparedStatement.setInt(3, pieceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBySquare(final Square square) {
        final String query = "DELETE FROM board WHERE piece_file = ? AND piece_rank = ?";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.getFile().getFile());
            preparedStatement.setString(2, square.getRank().getRank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        final String query = "DELETE FROM board";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
