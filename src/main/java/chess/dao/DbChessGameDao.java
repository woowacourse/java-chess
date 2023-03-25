package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.File;
import chess.domain.game.PieceMapper;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbChessGameDao implements ChessDao {

    public Connection getConnection() {
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
    public void save(final ChessGame chessGame) {
        final String sql = "INSERT INTO chess_game(piece_type, piece_file, piece_rank, piece_team, last_turn) VALUES (?, ?, ?, ?, ?)";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final Map<Position, Piece> board = chessGame.getBoard();
            for (Map.Entry<Position, Piece> entry : board.entrySet()) {
                preparedStatement.setString(1, entry.getValue().getPieceType().name());
                preparedStatement.setString(2, entry.getKey().getFile().name());
                preparedStatement.setString(3, entry.getKey().getRank().name());
                preparedStatement.setString(4, entry.getValue().getTeam().name());
                preparedStatement.setString(5, chessGame.getTurn().name());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGame find() {
        final String sql = "SELECT piece_type, piece_file, piece_rank, piece_team, last_turn FROM chess_game";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final Map<Position, Piece> board = new HashMap<>();
            Turn turn = null;

            if (resultSet.next()) {
                final PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                final File file = File.valueOf(resultSet.getString("piece_file"));
                final Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
                final Team team = Team.valueOf(resultSet.getString("piece_team"));
                turn = Turn.of(Team.valueOf(resultSet.getString("last_turn")));

                board.put(Position.of(file, rank), PieceMapper.get(pieceType, team));
            }

            return ChessGame.from(board, turn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(final ChessGame chessGame) {

    }
}
