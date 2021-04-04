package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PieceDAO {

    private final ConnectionFactory factory;

    public PieceDAO() {
        factory = new ConnectionFactory();
    }

    public Long save(Long chessGameId, Piece piece) {
        try (Connection con = factory.getConnection()) {
            String query = "INSERT INTO piece(color, shape, chess_game_id, row, col) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, piece.getColor().toString());
            preparedStatement.setString(2, piece.getShape().toString());
            preparedStatement.setLong(3, chessGameId);
            preparedStatement.setInt(4, piece.getRow());
            preparedStatement.setInt(5, piece.getColumn());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(Statement.RETURN_GENERATED_KEYS);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Piece가 제대로 생성되지 않았습니다");
    }

    public void saveAll(final Long chessGameId, final List<Piece> pieces) {
        for (final Piece piece : pieces) {
            save(chessGameId, piece);
        }
    }

    public List<Piece> findAllPiecesByChessGameId(Long chessGameId) {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM piece WHERE chess_game_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, chessGameId);
            ResultSet rs = preparedStatement.executeQuery();

            List<Piece> pieces = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String color = rs.getString("color");
                String shape = rs.getString("shape");
                rs.getLong("chess_game_id");
                int row = rs.getInt("row");
                int col = rs.getInt("col");
                Piece piece = PieceFactory.createPiece(shape, color, row, col);
                piece.setId(id);
                pieces.add(piece);
            }
            return pieces;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Optional<Piece> findOneByPosition(final Long chessGameId, final int row, final int col) {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM piece WHERE chess_game_id = ? AND row = ? AND col = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, chessGameId);
            preparedStatement.setLong(2, row);
            preparedStatement.setLong(3, col);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                Piece piece = PieceFactory.createPiece(
                        rs.getString("shape"), rs.getString("color"),
                        rs.getInt("row"), rs.getInt("col")
                );
                piece.setId(rs.getLong("id"));
                return Optional.of(piece);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(final Piece piece) {
        try (Connection con = factory.getConnection()) {
            String query = "UPDATE piece SET row = ?, col = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, piece.getRow());
            preparedStatement.setInt(2, piece.getColumn());
            preparedStatement.setLong(3, piece.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final Long chessGameId, final int row, final int col) {
        try (Connection con = factory.getConnection()) {
            String query = "DELETE FROM piece WHERE chess_game_id = ? AND row = ? AND col = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, chessGameId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, col);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
