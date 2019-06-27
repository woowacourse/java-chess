package chess.dao;

import chess.domain.Point;
import chess.dto.PieceDto;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PieceDao {
    private static final String INSERT_PIECE = "INSERT INTO piece(game_id, name, x, y, team) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PIECE = "SELECT name, x, y, team FROM piece WHERE game_id = ?";
    private static final String UPDATE_PIECE_BY_POSITION = "UPDATE piece SET x = ?, y = ? WHERE game_id = ? and x = ? and y = ?";
    private static final String DELETE_PIECE_BY_POSITION = "DELETE FROM piece WHERE game_id = ? and x = ? and y = ?";
    private static final String INSERT_BLANK_BY_POSITION = "INSERT INTO piece(game_id, name, x, y, team) VALUES (?, ?, ?, ?, ?)";

    private static PieceDao pieceDao;

    private ChessJdbcTemplate chessJdbcTemplate;

    private PieceDao(DataSource dataSource) {
        this.chessJdbcTemplate = ChessJdbcTemplate.getInstance(dataSource);
    }

    public static PieceDao getInstance(DataSource dataSource) {
        if (pieceDao == null) {
            pieceDao = new PieceDao(dataSource);
        }
        return pieceDao;
    }

    public void add(int gameId, PieceDto pieceDto) {
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(gameId);
        queryValues.add(pieceDto.getName());
        queryValues.add(pieceDto.getX());
        queryValues.add(pieceDto.getY());
        queryValues.add(pieceDto.isTeam());
        chessJdbcTemplate.executeUpdate(INSERT_PIECE, queryValues);
    }

    public List<PieceDto> findPieceById(int gameId) {
        List<Object> queryValues = Arrays.asList(gameId);
        List<Map<String, Object>> results = chessJdbcTemplate.executeQuery(SELECT_PIECE, queryValues);
        List<PieceDto> pieceDtos = new ArrayList<>();
        convertToDto(results, pieceDtos);
        return pieceDtos;
    }

    private void convertToDto(List<Map<String, Object>> results, List<PieceDto> pieceDtos) {
        for (Map<String, Object> result : results) {
            String name = (String) result.get("name");
            int x = (int) result.get("x");
            int y = (int) result.get("y");
            boolean team = (boolean) result.get("team");
            PieceDto pieceDto = new PieceDto(x, y, name, team);
            pieceDtos.add(pieceDto);
        }
    }

    public void updatePosition(int gameId, Point start, Point end) {
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(end.getPositionX());
        queryValues.add(end.getPositionY());
        queryValues.add(gameId);
        queryValues.add(start.getPositionX());
        queryValues.add(start.getPositionY());
        chessJdbcTemplate.executeUpdate(UPDATE_PIECE_BY_POSITION, queryValues);
    }

    public void insertBlank(int gameId, Point target) {
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(gameId);
        queryValues.add("BLANK");
        queryValues.add(target.getPositionX());
        queryValues.add(target.getPositionY());
        queryValues.add(false);
        chessJdbcTemplate.executeUpdate(INSERT_BLANK_BY_POSITION, queryValues);
    }

    public void deletePieceByPosition(int gameId, Point target) {
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(gameId);
        queryValues.add(target.getPositionX());
        queryValues.add(target.getPositionY());
        chessJdbcTemplate.executeUpdate(DELETE_PIECE_BY_POSITION, queryValues);
    }
}
