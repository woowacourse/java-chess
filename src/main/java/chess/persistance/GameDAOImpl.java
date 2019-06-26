package chess.persistance;

import chess.model.ChessPieceColor;

import java.util.*;

public class GameDAOImpl implements GameDAO {
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();
    private static final GameDAOImpl INSTANCE = new GameDAOImpl();

    private GameDAOImpl() {
    }

    public static GameDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void addGame(final String gameName) {
        String query = "INSERT INTO game (name, turn) VALUES (?, ?)";
        List<String> args = new ArrayList<>(Arrays.asList(gameName, ChessPieceColor.WHITE.name()));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    @Override
    public void removeGame(final int gameId) {
        String query = "DELETE FROM game WHERE id=?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(gameId)));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    @Override
    public int getGameId(final String gameName) {
        String query = "SELECT id FROM game WHERE name=?";
        List<String> args = new ArrayList<>(Collections.singletonList(gameName));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);

        return Integer.valueOf(result.get(0).get("id"));
    }

    @Override
    public ChessPieceColor getTurn(final int gameId) {
        String query = "SELECT turn FROM game WHERE id=?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(gameId)));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);
        return ChessPieceColor.valueOf(result.get(0).get("turn"));
    }

    @Override
    public int countGames() {
        String query = "SELECT COUNT(*) as num FROM game";
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, null);
        return Integer.valueOf(result.get(0).get("num"));
    }

    @Override
    public List<Integer> getAllIds() {
        String query = "SELECT * FROM game";
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, null);
        List<Integer> ids = new ArrayList<>();
        for (Map<String, String> map : result) {
            ids.add(Integer.valueOf(map.get("id")));
        }
        return ids;
    }

    @Override
    public String getName(final Integer id) {
        String query = "SELECT name FROM game WHERE id=?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(id)));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);
        return result.get(0).get("name");
    }

    @Override
    public void setTurn(final ChessPieceColor turn, final String gameId) {
        String query = "UPDATE game SET turn = ? WHERE id=?";
        List<String> args = new ArrayList<>(Arrays.asList(turn.name(), gameId));
        JDBC_TEMPLATE.updateQuery(query, args);
    }
}

