package dao;

import chess.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DBChessGameDao implements ChessGameDao {

    private final DBConnection dbConnection = new DBConnection();

    @Override
    public void createPiece(final Position position, final Piece piece, final String gameId) {
        final var query = "INSERT INTO chess_game (position, piece_type, piece_color, game_id) VALUES (?, ?, ?, ?);";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, convertPosition(position));
            preparedStatement.setString(2, piece.type().name());
            preparedStatement.setString(3, piece.color().name());
            preparedStatement.setInt(4, Integer.parseInt(gameId));

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임 결과를 저장하는데 실패했습니다.");
        }
    }

    @Override
    public Map<Position, Piece> read(final String gameId) {
        final Map<Position, Piece> board = new HashMap<>();

        final var query = "SELECT * FROM chess_game WHERE game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(gameId));
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String positionString = resultSet.getString("position");
                final List<String> positions = List.of(positionString.split(""));
                final Position position = Position.of(positions.get(0), positions.get(1));

                final PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                final Color color = Color.valueOf(resultSet.getString("piece_color"));

                Piece piece = pieceType.generate(color);

                board.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임 결과를 불러오는데 실패했습니다.");
        }
        return board;
    }

    @Override
    public void update(final Position from, final Position to, final String gameId) {
        final Piece fromPiece = readPieceOf(from, gameId);
        deletePiece(from, gameId);
        deletePiece(to, gameId);
        createPiece(to, fromPiece, gameId);
    }

    private Piece readPieceOf(final Position position, final String gameId) {
        final var query = "SELECT piece_type, piece_color FROM chess_game WHERE POSITION=? AND game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, convertPosition(position));
            preparedStatement.setInt(2, Integer.parseInt(gameId));

            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                final Color pieceColor = Color.valueOf(resultSet.getString("piece_color"));

                return pieceType.generate(pieceColor);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException("특정 위치의 체스말을 찾는 데 실패했습니다.");
        }
        return null;
    }

    private void deletePiece(final Position position, final String gameId) {
        final var query = "DELETE FROM chess_game WHERE position=? AND game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, convertPosition(position));
            preparedStatement.setInt(2, Integer.parseInt(gameId));

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException("게임을 초기화하는데 실패했습니다.");
        }
    }

    @Override
    public void deleteAll(final String gameId) {
        final var query = "DELETE FROM chess_game WHERE game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException("게임을 초기화하는데 실패했습니다.");
        }
    }

    private static String convertPosition(final Position position) {
        return position.file().command() + position.rank().command();
    }
}
