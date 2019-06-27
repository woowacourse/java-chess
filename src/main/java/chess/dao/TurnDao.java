package chess.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TurnDao {
    private static final String SELECT_CURRENT_TURN_BY_ROUND = "select current_team from turn where round = ?";
    private static final String INSERT_FIRST_TURN_BY_ROUND = "insert into turn (current_team, round) values (?, ?)";
    private static final String UPDATE_CURRENT_TURN_BY_ROUND = "update turn set current_team = ? where round = ?";
    private static final String WHITE_TEAM = "WHITE";
    private static final String BLACK_TEAM = "BLACK";
    private static final String CURRENT_ORDER_TEAM = "current_team";
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();

    public TurnDao() {
    }

    public void addFirstTurn(int round) {
        JDBC_TEMPLATE.updateQuery(INSERT_FIRST_TURN_BY_ROUND, WHITE_TEAM, round);
    }

    public String selectCurrentTurn(int round) {
        List<Map<String, String>> results = JDBC_TEMPLATE.selectQuery(SELECT_CURRENT_TURN_BY_ROUND, round);
        if (results.size() == 0) {
            return WHITE_TEAM;
        }
        return results.get(0).get(CURRENT_ORDER_TEAM);
    }

    public void updateCurrentTurn(int round) throws SQLException {
        String currentTeam = selectCurrentTurn(round);
        currentTeam = changeTeam(currentTeam);

        JDBC_TEMPLATE.updateQuery(UPDATE_CURRENT_TURN_BY_ROUND, currentTeam, round);
    }

    private String changeTeam(String currentTeam) {
        if (WHITE_TEAM.equals(currentTeam)) {
            return BLACK_TEAM;
        }
        return WHITE_TEAM;
    }
}
