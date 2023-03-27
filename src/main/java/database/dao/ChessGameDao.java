package database.dao;

import database.connection.ConnectionGenerator;
import domain.ChessGame;
import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map.Entry;

public final class ChessGameDao {
    public void saveBoard(ChessGame chessGame, String boardName) {
        final long boardId = saveBoardTeamAndReturnBoardId(boardName, chessGame.getTeam());
        final var query = "insert into board_pieces(board_id, type, position) values(?, ?, ?)";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Entry<Position, Piece> entry : chessGame.getPieces().entrySet()) {
                preparedStatement.setLong(1, boardId);
                preparedStatement.setString(2, entry.getValue().getName());
                preparedStatement.setString(3, entry.getKey().getName());
                preparedStatement.execute();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long saveBoardTeamAndReturnBoardId(String boardName, Team team) {
        final var query = "insert into board(board_name, start_team) value(?, ?);";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, boardName);
            preparedStatement.setString(2, team.name());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("방번호: " + resultSet.getLong(1));
                return resultSet.getLong(1);
            }
            throw new IllegalArgumentException("다른 방번호를 입력해 보세요");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collections findPiecesByBoardId(final Long boardId) {
        return null;
    }

    public Team findStartTeamByBoardId(final Long boardId) {
        return null;
    }
}
