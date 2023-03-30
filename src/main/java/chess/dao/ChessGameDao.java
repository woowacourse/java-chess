package chess.dao;

import chess.config.DatabaseConfig;
import chess.domain.board.Board;
import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.ChessGameState;
import chess.domain.chessGame.ChessGameStateType;
import chess.domain.chessGame.ReadyChessGameState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDao {
    public Connection getConnection() throws IOException {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        return databaseConfig.getConnection();
    }

    public void save(final ChessGame chessGame) {
        final var query = "INSERT INTO chess_game(turn, status, piece_type, piece_position, piece_color) VALUES(?, ?, ?, ?, ?)";
        Map<Position, Piece> board = chessGame.getBoard();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, chessGame.getTurnName());
                preparedStatement.setString(2, chessGame.getStatusName());
                preparedStatement.setString(3, piece.getPieceTypeName());
                preparedStatement.setString(4, position.getSymbol());
                preparedStatement.setString(5, piece.getColorName());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete() {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(final ChessGame chessGame, Position position, Piece piece) {
        final var query = "UPDATE chess_game SET piece_type = ?, piece_color = ? WHERE piece_position = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.getPieceTypeName());
            preparedStatement.setString(2, piece.getColorName());
            preparedStatement.setString(3, position.getSymbol());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessGame select() {
        Map<Position, Piece> board = new HashMap<>();
        Color turn = Color.WHITE;
        ChessGameState chessGameState = new ReadyChessGameState();

        final var query = "SELECT turn, status, piece_type, piece_position, piece_color FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                turn = Color.valueOf(resultSet.getString("turn"));
                chessGameState = ChessGameStateType.findByName(resultSet.getString("status"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                Position position = Position.from(resultSet.getString("piece_position"));
                Color pieceColor = Color.valueOf(resultSet.getString("piece_color"));
                Piece piece = pieceType.getPiece(pieceColor);
                board.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ChessGame(new Board(board), chessGameState, turn);
    }
}
