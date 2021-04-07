package chess.dao;

import java.util.List;

public class ChessDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public ChessDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addChessGame(String gameId, String data) {
        String query = "INSERT INTO chess VALUES (?, ?)";

        jdbcTemplate.update(query, gameId, data);
    }

    public void updateChessGame(String gameId, String data) {
        String query = "UPDATE chess SET data=? WHERE game_id = ?";

        jdbcTemplate.update(query, data, gameId);
    }

    public String findChessGameByGameId(String gameId) {
        String query = "SELECT data FROM chess WHERE game_id = ?";

        List<String> data = jdbcTemplate.query(query, (rs, i) -> rs.getString("data"), gameId);

        if (data.size() == 0) {
            throw new IllegalArgumentException("데이터가 존재하지 않습니다.");
        }

        return data.get(0);
    }

    public boolean isGameIdExisting(String gameId) {
        String query = "SELECT count(*) as count FROM chess WHERE game_id = ?";

        List<Integer> count = jdbcTemplate.query(query, (rs, i) -> rs.getInt("count"), gameId);

        return count.get(0) != 0;
    }

}
