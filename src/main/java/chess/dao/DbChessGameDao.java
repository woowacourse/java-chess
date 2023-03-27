package chess.dao;

import chess.dto.game.ChessGameLoadDto;
import chess.dto.game.ChessGameSaveDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DbChessGameDao implements ChessDao {

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:13306/chess?useSSL=false&serverTimezone=UTC",
                    "root",
                    "root"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(final ChessGameSaveDto dto) {
        final String sql = "INSERT INTO chess_game(piece_type, piece_file, piece_rank, piece_team, last_turn) VALUES (?, ?, ?, ?, ?)";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
        ) {

            for (int i = 0; i < dto.getSize(); i++) {
                preparedStatement.setString(1, dto.getPieceTypes().get(i));
                preparedStatement.setString(2, dto.getPieceFiles().get(i));
                preparedStatement.setString(3, dto.getPieceRanks().get(i));
                preparedStatement.setString(4, dto.getPieceTeams().get(i));
                preparedStatement.setString(5, dto.getLastTurns().get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGameLoadDto loadGame() {
        final String sql = "SELECT piece_type, piece_file, piece_rank, piece_team, last_turn FROM chess_game";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
             final ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            final List<String> piece_type = new ArrayList<>();
            final List<String> piece_file = new ArrayList<>();
            final List<String> piece_ranks = new ArrayList<>();
            final List<String> piece_teams = new ArrayList<>();
            String last_turn = "";

            while (resultSet.next()) {
                piece_type.add(resultSet.getString("piece_type"));
                piece_file.add(resultSet.getString("piece_file"));
                piece_ranks.add(resultSet.getString("piece_rank"));
                piece_teams.add(resultSet.getString("piece_team"));
                last_turn = resultSet.getString("last_turn");
            }
            return new ChessGameLoadDto(piece_type, piece_file, piece_ranks, piece_teams, last_turn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasHistory() {
        final String sql = "SELECT piece_type, piece_file, piece_rank, piece_team, last_turn FROM chess_game";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
             final ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        final String sql = "DELETE FROM chess_game";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
