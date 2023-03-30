package chess.controller.dao;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ChessGameDao {

    DBConnection dbConnection = new DBConnection();

    public void save(ChessGame chessGame) {
        deleteChessGame();
        saveGame(chessGame);
        savePieces(chessGame);
    }

    public void deleteChessGame() {
        deletePiece();
        delete();
    }

    private void saveGame(ChessGame chessGame) {
        final String query = "insert into chess_game(tern) values(?)";
        try (
                final var connection = dbConnection.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGame.getTurn().oppositeTurn().toString());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void savePieces(ChessGame chessGame) {
        Map<Square, Piece> board = chessGame.getBoard().getBoard();
        for (Map.Entry<Square, Piece> squareAndPieces : board.entrySet()) {
            addBoardDao(squareAndPieces.getKey(), squareAndPieces.getValue());
        }
    }

    private void addBoardDao(Square square, Piece piece) {
        final String query = "insert into piece(chess_game_id, x, y, color, type) values(?, ?, ?, ?, ?)";
        try (
                final var connection = dbConnection.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, square.getFile().name());
            preparedStatement.setString(3, square.getRank().name());
            preparedStatement.setString(4, piece.getColor().name());
            preparedStatement.setString(5, piece.getType());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePiece() {
        final String queryGetGame = "truncate piece";
        try (
                final var connection = dbConnection.getConnection();
                final var preparedStatement = connection.prepareStatement(queryGetGame)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete() {
        final String queryGetGame = "truncate chess_game";
        try (
                final var connection = dbConnection.getConnection();
                final var preparedStatement = connection.prepareStatement(queryGetGame)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessGame findAll() {
        final String queryGetGame = "select * from chess_game;";
        try (
                final var connection = dbConnection.getConnection();
                final var preparedStatement = connection.prepareStatement(queryGetGame)) {
            ResultSet resultSetForGame = preparedStatement.executeQuery();
            if (resultSetForGame.next()) {
                ChessBoardDao chessBoardDao = new ChessBoardDao();
                Board board = chessBoardDao.findAll();
                Color turn = Color.valueOf(resultSetForGame.getString(2));
                return new ChessGame(board, turn);
            }
            return new ChessGame(BoardFactory.generate(), Color.WHITE);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
