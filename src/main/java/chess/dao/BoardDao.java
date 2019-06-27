package chess.dao;

import chess.dto.BoardDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDao {
    private static final String INSERT_BOARD_SQL = "INSERT INTO board(piece,team,point,round) values(?,?,?,?)";
    private static final String SELECT_CURRENT_ROUND = "select round from board order by round desc limit 1";
    private static final String SELECT_CHESSES = "select piece, team, point from board where round = ?";
    private static final String DELETE_PIECE = "delete from board where round = ? and point = ?";
    private static final String UPDATE_PIECE = "update board set point = ? where point = ? and round = ?";
    private static final int ZERO_ROUND = 0;
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();

    public BoardDao() {
    }

    public void initialize(List<BoardDto> boardDtos) {
        for (BoardDto boardDto : boardDtos) {
            JDBC_TEMPLATE.updateQuery(INSERT_BOARD_SQL, boardDto.getPiece(), boardDto.getTeam(), boardDto.getPoint(), boardDto.getRound());
        }

    }

    public int recentRound() {
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(SELECT_CURRENT_ROUND);
        if (result.size() == 0) {
            return ZERO_ROUND;
        }
        return Integer.valueOf(result.get(0).get("round"));
    }

    public List<BoardDto> findChessesByRound(int round) {
        List<BoardDto> chesses = new ArrayList<>();
        List<Map<String, String>> results = JDBC_TEMPLATE.selectQuery(SELECT_CHESSES, round);

        for (Map<String, String> result : results) {
            String piece = result.get("piece");
            String team = result.get("team");
            String point = result.get("point");
            chesses.add(new BoardDto(piece, team, point, round));
        }

        return chesses;
    }

    public void remove(int round, String destination) {
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        jdbcTemplate.updateQuery(DELETE_PIECE, round, destination);
    }

    public void update(int round, String source, String destination) {
        JDBC_TEMPLATE.updateQuery(UPDATE_PIECE, destination, source, round);
    }
}
