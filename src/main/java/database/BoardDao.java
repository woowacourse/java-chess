package database;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Role;
import chess.domain.piece.Team;
import database.dto.SquareDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BoardDao {

    private final JdbcReadOnlyConnector readOnlyConnector = new JdbcReadOnlyConnector();
    private final JdbcTransactionConnector transactionConnector = new JdbcTransactionConnector();

    public void save(List<SquareDto> squareDtos, int gameId) {
        String query = "INSERT INTO board VALUES (?, ?, ?, ?, ?, ?) ";
        String id = String.valueOf(generateId());
        for (SquareDto squareDto : squareDtos) {
            List<String> parameters = List.of(id, String.valueOf(gameId),
                    String.valueOf(squareDto.getX_pos()), String.valueOf(squareDto.getY_pos()),
                    squareDto.getRole(), squareDto.getTeam());
            transactionConnector.executeUpdate(query, parameters);
        }
    }

    public void update(List<SquareDto> squareDtos, int gameId) {
        String query = "UPDATE board SET role = ?, team = ? " +
                "WHERE game_id = ? AND x_pos = ? AND y_pos = ?";

        for (SquareDto squareDto : squareDtos) {
            List<String> parameters = List.of(squareDto.getRole(), squareDto.getTeam(),
                    String.valueOf(gameId), String.valueOf(squareDto.getX_pos()), String.valueOf(squareDto.getY_pos()));
            transactionConnector.executeUpdate(query, parameters);
        }
    }

    public Map<Position, Piece> findByGameId(int gameId) {
        String query = "SELECT x_pos, y_pos, role, team FROM board WHERE game_id = ?";

        List<String> parameters = List.of(String.valueOf(gameId));

        return (Map<Position, Piece>) readOnlyConnector.executeQuery(query, parameters, this::convertToBoard);
    }

    private Map<Position, Piece> convertToBoard(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> squares = new HashMap<>(64);
        while (resultSet.next()) {
            int x_pos = resultSet.getInt("x_pos");
            int y_pos = resultSet.getInt("y_pos");
            String role = resultSet.getString("role");
            String team = resultSet.getString("team");
            Position position = Position.of(x_pos, y_pos);
            Piece piece = PieceFactory.of(Role.valueOf(role), Team.valueOf(team));
            squares.put(position, piece);
        }
        return squares;
    }

    public void delete(int id) {
        String query = "DELETE FROM board WHERE game_id = ?";
        List<String> parameters = List.of(String.valueOf(id));
        transactionConnector.executeUpdate(query, parameters);
    }

    private int generateId() {
        List<Integer> allIds;
        try {
            allIds = findAllIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (allIds.isEmpty()) {
            return 1;
        }
        return Collections.max(allIds) + 1;
    }

    private List<Integer> findAllIds() throws SQLException {
        String query = "SELECT distinct(id) FROM board";
        return (List<Integer>) readOnlyConnector.executeQuery(query, resultSet -> {
            List<Integer> allBoardIds = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                allBoardIds.add(id);
            }
            return allBoardIds;
        });
    }
}
