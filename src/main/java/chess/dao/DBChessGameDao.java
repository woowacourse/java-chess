package chess.dao;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DBChessGameDao implements ChessGameDao {

    private final DBBoardDao dbBoardDao;

    public DBChessGameDao(DBBoardDao dbBoardDao) {
        this.dbBoardDao = dbBoardDao;
    }

    @Override
    public int save(final ChessGame chessGame) {
        final var query = "INSERT INTO chess_game(turn) VALUES (?);";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, chessGame.getTurn().name());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            final int id = resultSet.getInt(1);
            dbBoardDao.save(id, chessGame.getBoard());
            return id;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGame select(final int id) {
        final var query = "SELECT * FROM chess_game WHERE id = ?";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Team turn = Team.valueOf(resultSet.getString("turn"));
                final Board board = dbBoardDao.select(id);
                return new ChessGame(id, board, turn);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Integer> selectAllId() {
        List<Integer> id = new ArrayList<>();
        final var query = "SELECT id FROM chess_game";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id.add(resultSet.getInt("id"));
            }

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void update(final ChessGame chessGame) {
        final var query = "UPDATE chess_game SET turn = ? WHERE id = ?;";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGame.getTurn().name());
            preparedStatement.setInt(2, chessGame.getId());
            preparedStatement.executeUpdate();
            dbBoardDao.update(chessGame.getId(), chessGame.getBoard());
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(final int id) {
        final var query = "DELETE FROM chess_game WHERE id = ?;";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
