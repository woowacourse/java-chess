package chess.controller.dao;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {

    DBConnection dbConnection = new DBConnection();

    public void save(ChessGame chessGame) {
        deletePiece(chessGame);
        delete(chessGame);
        saveGame(chessGame);
        savePiece(chessGame);
        final String queryGetGame = "";
    }

    private void savePiece(ChessGame chessGame) {

    }

    private void saveGame(ChessGame chessGame) {

    }

    private void deletePiece(ChessGame chessGame) {
        final String queryGetGame = "delete from piece";
        try (
            final var connection = dbConnection.getConnection();
            final var preparedStatement = connection.prepareStatement(queryGetGame)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(ChessGame chessGame) {
        final String queryGetGame = "delete from chess_game";
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
                String turnString = resultSetForGame.getString(2);
                Color turn = Color.valueOf(turnString);
                return new ChessGame(board, turn);
            } else {
                return new ChessGame(BoardFactory.generate(), Color.WHITE);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
