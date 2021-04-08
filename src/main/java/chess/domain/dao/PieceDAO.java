package chess.domain.dao;

import chess.domain.board.Board;
import chess.domain.dto.PiecesDto;
import chess.domain.game.BlackTurn;
import chess.domain.game.ChessGame;
import chess.domain.game.State;
import chess.domain.game.WhiteTurn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDAO {

    DBConnection dbConnection;

    public PieceDAO() {
        dbConnection = new DBConnection();
    }

    public void save(final Piece piece) {
        String color = piece.getColor().toString();
        String shape = piece.getShape().toString();
        if ("WHITE".equals(color)) {
            shape = shape.toLowerCase();
        }
        String query = "INSERT INTO pieces(color, shape, position) VALUES(?,?,?)";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, color);
            preparedStatement.setString(2, shape);
            preparedStatement.setString(3, piece.getPosition().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void saveAll(List<Piece> pieces) {
        deleteAll();
        for (Piece piece : pieces) {
            save(piece);
        }
    }

    private void deleteAll() {
        String query = "DELETE FROM pieces";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);) {

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Board loadPieces() {
        String query = "SELECT * FROM pieces";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();
            List<Piece> pieces = new ArrayList<>();

            while (rs.next()) {
                String color = rs.getString("color");
                String shape = rs.getString("shape");
                String position = rs.getString("position");
                System.out.println("color : " + color + " shape : " + shape + " position : " + position);

                pieces.add(PieceFactory.createPiece(color, shape, new Position(position)));
            }
            return new Board(pieces);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public State loadTurn(ChessGame chessGame) {
        String query = "SELECT * FROM turn";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            String turn = "";
            while (rs.next()) {
                turn = rs.getString("turn");
            }
            if ("black".equals(turn)) {
                return new BlackTurn(chessGame);
            }
            return new WhiteTurn(chessGame);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean saveTurn(String status) {
        deleteTurn();
        String query = "INSERT INTO turn(turn) values (?)";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    private void deleteTurn() {
        String query = "DELETE FROM turn";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
