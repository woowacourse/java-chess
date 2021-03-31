package chess.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.exception.NotFoundPieceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDAO {

    private final ConnectionFactory factory;

    public PieceDAO() {
        factory = new ConnectionFactory();
    }

    public void save(Long chessGameId, Piece piece) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "INSERT INTO pieces(color, shape, chess_game_id, row, col) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, piece.getColor().toString());
            preparedStatement.setString(2, piece.getShape().toString());
            preparedStatement.setLong(3, chessGameId);
            preparedStatement.setInt(4, piece.getRow());
            preparedStatement.setInt(5, piece.getColumn());
            preparedStatement.executeUpdate();
        }

    }

    public List<Piece> findAllPiecesByChessGameId(Long chessGameId) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM pieces WHERE chess_game_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, chessGameId);
            ResultSet rs = preparedStatement.executeQuery();

            List<Piece> pieces = new ArrayList<>();
            while (rs.next()) {
                String color = rs.getString("color");
                String shape = rs.getString("shape");
                rs.getLong("chess_game_id");
                int row = rs.getInt("row");
                int col = rs.getInt("col");
                pieces.add(PieceFactory.createPiece(shape, color, row, col));
            }

            return pieces;
        }
    }

    public Piece findOneByPosition(final Long chessGameId, final int row, final int col) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM pieces WHERE chess_game_id = ? AND row = ? AND col = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, chessGameId);
            preparedStatement.setLong(2, row);
            preparedStatement.setLong(3, col);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                throw new NotFoundPieceException();
            }

            return PieceFactory.createPiece(
                    rs.getString("shape"), rs.getString("color"),
                    rs.getInt("row"), rs.getInt("col")
            );
        }

    }

    public void update(final Long chessGameId, final int sourceRow, final int sourceCol,
                       final int targetRow, final int targetColumn) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "UPDATE pieces SET row = ?, col = ? WHERE chess_game_id = ? AND row = ? AND col = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, targetRow);
            preparedStatement.setInt(2, targetColumn);
            preparedStatement.setLong(3, chessGameId);
            preparedStatement.setInt(4, sourceRow);
            preparedStatement.setInt(5, sourceCol);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(final Long chessGameId, final int row, final int col) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "DELETE FROM pieces WHERE chess_game_id = ? AND row = ? AND col = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, chessGameId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, col);
            preparedStatement.executeUpdate();
        }
    }

}
