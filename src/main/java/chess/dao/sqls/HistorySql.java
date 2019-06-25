package chess.dao.sqls;

public class HistorySql {
    public static final String SELECT_LAST_HISTORY_BY_ROUND = 
            "SELECT round_id, row0, row1, row2, row3, row4, row5, row6, row7, turn " +
                    "FROM history " +
                    "WHERE round_id = ? " +
                    "AND turn = (SELECT MAX(turn) " +
                                "FROM history " +
                                "GROUP BY round_id " +
                                "HAVING round_id = ?)";

    public static final String INSERT_HISTORY =
            "INSERT INTO history(round_id, row0, row1, row2, row3, row4, row5, row6, row7, turn) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
