package domain.dao;

import common.JdbcContext;
import domain.type.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class BoardDaoImpl implements BoardDao {

    private static final String FIND_COLOR_SQL = "select `color` from board where `id` = ?";
    private static final String COUNT_SQL = "select count(*) from board where `id` = ?";
    private static final String INSERT_SQL = "insert into board (`id`, `color`) values (?, ?)";
    private static final String UPDATE_COLOR_SQL = "update board set `color` = ? where `id` = ?";
    private final JdbcContext jdbcContext;

    public BoardDaoImpl(final JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @Override
    public Color findLastColor(final String id) {
        return jdbcContext.workWithTransaction(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_COLOR_SQL);
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String color = resultSet.getString("color");
                return Color.findByName(color);
            }
            return Color.NONE;
        });
    }

    @Override
    public Integer count(final String id) {
        return jdbcContext.workWithTransaction(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SQL);
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count(*)");
            }
            return 0;
        });
    }

    @Override
    public Void insert(final String boardId, final Color color) {
        return jdbcContext.workWithTransaction(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, boardId);
            preparedStatement.setString(2, color.name());
            preparedStatement.execute();
            return null;
        });
    }

    @Override
    public Integer update(final String boardId, final Color color) {
        return jdbcContext.workWithTransaction(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COLOR_SQL);
            preparedStatement.setString(1, color.name());
            preparedStatement.setString(2, boardId);
            return preparedStatement.executeUpdate();
        });
    }
}
