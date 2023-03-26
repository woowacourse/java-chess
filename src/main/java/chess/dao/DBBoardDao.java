package chess.dao;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class DBBoardDao {

    public void save(final int chessGameId, final Board board) {
        final Map<Position, Piece> positionsAndPieces = board.getBoard();
        for (final Map.Entry<Position, Piece> entry : positionsAndPieces.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();

            final var query = "INSERT INTO board(chess_game_id, position_column, position_row, piece_type, piece_team) VALUES(?,?,?,?,?);";
            try (final Connection connection = DBConnection.getConnection();
                 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, chessGameId);
                preparedStatement.setInt(2, position.getColumn());
                preparedStatement.setInt(3, position.getRow());
                preparedStatement.setString(4, piece.getPieceType().name());
                preparedStatement.setString(5, piece.getTeam().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Board select(final int chessGameId) {
        final Map<Position, Piece> board = new TreeMap<>();

        final var query = "SELECT * FROM board WHERE chess_game_id = ?;";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, chessGameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                final int column = resultSet.getInt("position_column");
                final int row = resultSet.getInt("position_row");

                final Position position = new Position(column, row);
                final PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                final Team pieceTeam = Team.valueOf(resultSet.getString("piece_team"));
                final Piece piece = PieceMapper.toPiece(pieceType, pieceTeam);

                board.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        if (board.isEmpty()) {
            return null;
        }
        return new Board(board);
    }

    public void update(final int chessGameId, final Board board) {
        delete(chessGameId);
        save(chessGameId, board);
    }

    public void delete(final int chessGameId) {
        final var query = "DELETE FROM board WHERE chess_game_id = ?;";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, chessGameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
