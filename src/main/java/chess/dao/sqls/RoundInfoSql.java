package chess.dao.sqls;

public class RoundInfoSql {
    public static final String SELECT_ALL_GAME_BY_IS_END =
            "SELECT round.id, p1.name 'black_player', p2.name 'white_player', round.is_end " +
                    "FROM round " +
                    "INNER JOIN player p1 ON p1.id = round.white_id " +
                    "INNER JOIN player p2 ON p2.id = round.black_id " +
                    "WHERE round.is_end = ?";

    public static final String INSERT_ROUND_INFO =
            "INSERT INTO round(white_id, black_id) VALUES( " +
                    "(SELECT id " +
                    "FROM player " +
                    "WHERE name = ?),  " +
                    "(SELECT id " +
                    "FROM player " +
                    "WHERE name = ?))";

    public static final String UPDATE_IS_END_TRUE =
            "UPDATE round SET is_end = true WHERE id = ?";

    public static final String SELECT_ROUND_INFO =
            "SELECT round.id, p1.name 'black_player', p2.name 'white_player', round.is_end " +
                    "FROM round " +
                    "INNER JOIN player p1 ON p1.id = round.white_id " +
                    "INNER JOIN player p2 ON p2.id = round.black_id " +
                    "WHERE round.id = ?";

    public static final String SELECT_GAME_RESULT =
            "SELECT round_id, name " +
                    "FROM result " +
                    "INNER JOIN player ON player.id = result.winner " +
                    "WHERE round_id = ?";
}
