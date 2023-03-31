package database;

import domain.board.Turn;
import domain.board.piece.Camp;
import domain.board.piece.Piece;
import domain.board.piece.PieceType;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Objects;

public class BoardDao {

    private final DatabaseConnector databaseConnector;

    public BoardDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public boolean isHistoryExist() {
        final String query = "SELECT * FROM BOARD WHERE id = 1";
        try (
            final Connection connection = databaseConnector.getConnection();
            final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            return resultSet.next();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }


    public void loadBoard(final Map<Location, Piece> board) {
        final String query = "SELECT * FROM BOARD";
        try (
            final Connection connection = databaseConnector.getConnection();
            final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Location location = Location.of(
                    Row.valueOf(resultSet.getInt(2)),
                    Column.valueOf(resultSet.getInt(3))
                );
                PieceType pieceType = PieceType.findByName(resultSet.getString(4));
                Camp camp = Camp.findByName(resultSet.getString(5));
                board.put(location, Piece.of(pieceType, camp));
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public Turn loadTurn() {
        final String query = "SELECT * FROM BOARD WHERE id = 1";
        try (
            final Connection connection = databaseConnector.getConnection();
            final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return Turn.byCampName(resultSet.getString(6));
            }
            throw new IllegalArgumentException("보드 진행 상황이 존재하지 않습니다.");
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void insertBoard(final Map<Location, Piece> board) {
        board.forEach((key, value) -> insertPiece(key, value, Turn.white()));
    }

    public void updateBoard(final Map<Location, Piece> board, final Turn turn) {
        board.forEach((key, value) -> updatePiece(key, value, turn));
    }

    private void insertPiece(final Location location, final Piece piece, final Turn turn) {
        final String query = "INSERT INTO BOARD VALUES(?,?,?,?,?,?)";
        try (
            final Connection connection = databaseConnector.getConnection();
            final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query)
        ) {
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, location.getRow());
            preparedStatement.setInt(3, location.getCol());
            preparedStatement.setString(4, piece.getType().name());
            preparedStatement.setString(5, piece.getCamp().name());
            preparedStatement.setString(6, turn.getCamp().name());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private void updatePiece(final Location location, final Piece piece, final Turn turn) {
        final String query = "UPDATE BOARD SET piece=?, camp=?, turn =? where board_row=? AND board_column=?";
        try (
            final Connection connection = databaseConnector.getConnection();
            final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query)
        ) {
            preparedStatement.setString(1, piece.getType().name());
            preparedStatement.setString(2, piece.getCamp().name());
            preparedStatement.setString(3, turn.getCamp().name());
            preparedStatement.setInt(4, location.getRow());
            preparedStatement.setInt(5, location.getCol());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
