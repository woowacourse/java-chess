package chess.dao;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.TeamColor;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao implements ChessGameDao {
    private static final DBConnection dbConnection = new DBConnection();

    @Override
    public void save(ChessGame chessGame) {
        Map<Position, Piece> board = chessGame.getChessBoard();
        for (Map.Entry<Position, Piece> boardEntry : board.entrySet()) {
            final var query = "INSERT INTO chess_game(piece_type, piece_rank, piece_file, team, turn) VALUES (?, ?, ?, ?, ?)";
            try (final var connection = dbConnection.getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, boardEntry.getValue().getPieceType().name());
                preparedStatement.setInt(2, boardEntry.getKey().getRank());
                preparedStatement.setInt(3, boardEntry.getKey().getFile());
                preparedStatement.setString(4, boardEntry.getValue().getTeamColor().name());
                preparedStatement.setString(5, chessGame.getCurrentTeamColor().name());
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        Map<Position, Piece> board = new HashMap<>();
        TeamColor turn = null;
        final var query = "SELECT piece_type, piece_rank, piece_file, team, turn FROM chess_game";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                int rank = resultSet.getInt("piece_rank");
                int file = resultSet.getInt("piece_file");
                TeamColor teamColor = TeamColor.valueOf(resultSet.getString("team"));
                turn = TeamColor.valueOf(resultSet.getString("turn"));
                Piece piece = PieceType.toPiece(pieceType, teamColor);
                board.put(Position.of(rank, file), piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        if (board.isEmpty()) {
            return null;
        }
        return new ChessGame(new ChessBoard(board), turn.changeTurn());
    }

    @Override
    public void update(ChessGame chessGame) {
        delete(chessGame);
        save(chessGame);
    }

    public void delete(ChessGame chessGame) {
        final var query = "DELETE from chess_game";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(ChessGame chessGame) {
        final var query = "TRUNCATE TABLE chess_game";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
