package database;

import chess.domain.piece.Team;
import chess.game.Turn;
import database.dto.ChessGameDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessGameDao {

    private final JdbcReadOnlyConnector readOnlyConnector = new JdbcReadOnlyConnector();
    private final JdbcTransactionConnector transactionConnector = new JdbcTransactionConnector();

    public int save(ChessGameDto chessGameDto) {
        String query = "INSERT INTO chess_game VALUES (?, ?, ?)";
        int id = generateId();
        List<String> parameters = List.of(String.valueOf(id), chessGameDto.getGameStatus(), chessGameDto.getTurn());

        transactionConnector.executeUpdate(query, parameters);
        return id;
    }

    public void update(ChessGameDto chessGameDto) {
        String query = "UPDATE chess_game SET game_status = ?, turn = ? WHERE id = ?";

        List<String> parameters = List.of(chessGameDto.getGameStatus(), chessGameDto.getTurn(), String.valueOf(chessGameDto.getGameId()));
        transactionConnector.executeUpdate(query, parameters);
    }

    public void delete(int id) {
        String query = "DELETE FROM chess_game WHERE id = ?";
        List<String> parameters = List.of(String.valueOf(id));

        transactionConnector.executeUpdate(query, parameters);
    }

    public Turn findTurnById(int game_id) {
        String query = "SELECT turn FROM chess_game WHERE id = ?";
        List<String> parameters = List.of(String.valueOf(game_id));

        return (Turn) readOnlyConnector.executeQuery(query, parameters, resultSet -> {
            String turn = null;
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
            }
            return new Turn(Team.valueOf(turn));
        });
    }

    public List<Integer> findContinuingGameIds() {
        String query = "SELECT id FROM chess_game WHERE game_status = ?";
        List<String> parameters = List.of("CONTINUING");

        return (List<Integer>) readOnlyConnector.executeQuery(query, parameters, resultSet -> {
            List<Integer> continuingGameIds = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                continuingGameIds.add(id);
            }
            return continuingGameIds;
        });
    }

    private int generateId() {
        List<Integer> gameIds = findAllGameId();
        if (gameIds.isEmpty()) {
            return 1;
        }
        return Collections.max(gameIds) + 1;
    }

    private List<Integer> findAllGameId() {
        String query = "SELECT id FROM chess_game";

        return (List<Integer>) readOnlyConnector.executeQuery(query, resultSet -> {
            List<Integer> allGameIds = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                allGameIds.add(id);
            }
            return allGameIds;
        });
    }
}
