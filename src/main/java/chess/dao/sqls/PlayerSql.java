package chess.dao.sqls;

public class PlayerSql {
    public static final String INSERT_PLAYER = 
            "INSERT INTO player (name)  " +
                    "  SELECT ? FROM DUAL " +
                    "WHERE NOT EXISTS  " +
                    "  (SELECT name FROM player WHERE name = ?)";
}
