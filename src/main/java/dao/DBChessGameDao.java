package dao;

import chess.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DBChessGameDao {

    private final DBConnection dbConnection = new DBConnection();

    public List<String> gameIds() {
        final var query = "SELECT game_id FROM chess_status;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            List<String> gameIds = new ArrayList<>();
            while (resultSet.next()) {
                final String gameId = resultSet.getString("game_id");
                gameIds.add(gameId);
            }

            return gameIds;
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임 상태를 저장하는데 실패했습니다.");
        }
    }

    public void saveChessGame(final ChessGame chessGame, final String gameId) {
        final Map<Position, Piece> board = chessGame.board();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();

            final var query = "INSERT INTO chess_game (position, piece_type, piece_color, game_id) VALUES (?, ?, ?, ?);";
            try (final var connection = dbConnection.getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, position.file().command() + position.rank().command());
                preparedStatement.setString(2, piece.type().name());
                preparedStatement.setString(3, piece.color().name());
                preparedStatement.setInt(4, Integer.parseInt(gameId));

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                System.out.println(e.getErrorCode());
                throw new IllegalStateException("체스 게임 결과를 저장하는데 실패했습니다.");
            }
        }
    }

    public Map<Position, Piece> selectBoard(final String gameId) {
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

                final String pieceTypeString = resultSet.getString("piece_type");
                if (pieceTypeString.isBlank()) {
                    continue;
                }
                final PieceType pieceType = PieceType.valueOf(pieceTypeString);

                final String pieceColorString = resultSet.getString("piece_color");
                if (pieceTypeString.isBlank()) {
                    continue;
                }
                final Color color = Color.valueOf(pieceColorString);

                Piece piece = pieceType.generate(color);

                board.put(position, piece);
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("체스 게임 결과를 불러오는데 실패했습니다.");
        }
        return board;
    }

    public void resetChessGame(final String gameId) {
        final var query = "DELETE FROM chess_game WHERE game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("게임을 초기화하는데 실패했습니다.");
        }
    }
}
