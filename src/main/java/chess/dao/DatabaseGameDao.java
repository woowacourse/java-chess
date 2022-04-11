package chess.dao;

import chess.dao.jdbcutil.JdbcUtil;
import chess.dao.jdbcutil.StatementExecutor;
import chess.service.dto.ChessGameDto;
import chess.service.dto.GamesDto;
import chess.service.dto.StatusDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseGameDao implements GameDao {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String TURN = "turn";

    @Override
    public void update(ChessGameDto dto) {
        String sql = "update game set status = ?, turn = ? where id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(dto.getStatus())
                .setString(dto.getTurn())
                .setInt(dto.getId())
                .executeUpdate();
    }

    @Override
    public ChessGameDto findById(int id) {
        String sql = "select id, name, status, turn from game where id = ?";
        return new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setInt(id)
                .findFirst(this::getChessGameDto);
    }

    private ChessGameDto getChessGameDto(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        String status = resultSet.getString(STATUS);
        String turn = resultSet.getString(TURN);
        return new ChessGameDto(id, name, status, turn);
    }

    @Override
    public void updateStatus(StatusDto statusDto, int id) {
        String sql = "update game set status = ? where id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(statusDto.getStatus())
                .setInt(id)
                .executeUpdate();
    }

    @Override
    public GamesDto findAll() {
        String sql = "select id, name, status, turn from game";
        List<ChessGameDto> games = new StatementExecutor(JdbcUtil.getConnection(), sql)
                .findAll(this::getChessGameDto);
        return new GamesDto(games);
    }

    @Override
    public void createGame(String name) {
        String sql = "insert into game set name = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(name)
                .executeUpdate();
    }
}
