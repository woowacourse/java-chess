package chess.dao;

import chess.domain.Board;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    private final ConnectionManager connectionManager;

    public BoardDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Board save(Board board) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into board (room_title) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, board.getTitle());
            preparedStatement.executeUpdate();
            final MemberDao memberDao = new MemberDao(new ConnectionManager());
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("보드가 없습니다.");
            }
            return new Board(
                    generatedKeys.getInt(1),
                    board.getTitle(),
                    memberDao.getAllByBoardId(generatedKeys.getInt(1)));
        });
    }

    public int deleteAll() {
        return connectionManager.executeQuery(connection -> {
            String sql = "delete from board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        });
    }

    public int deleteById(int id) {
        return connectionManager.executeQuery(connection -> {
            String sql = "delete from board where id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    public Board getById(int id) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select * from board where id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final MemberDao memberDao = new MemberDao(new ConnectionManager());
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 보드 없습니다.");
            }
            return new Board(
                    resultSet.getInt("id"),
                    resultSet.getString("room_title"),
                    memberDao.getAllByBoardId(resultSet.getInt("id"))
            );
        });
    }

    public List<Board> findAll() {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select * from board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final MemberDao memberDao = new MemberDao(connectionManager);
            List<Board> boards = new ArrayList<>();
            while (resultSet.next()) {
                boards.add(new Board(
                        resultSet.getInt("id"),
                        resultSet.getString("room_title"),
                        memberDao.getAllByBoardId(resultSet.getInt("id")))
                );
            }
            return boards;
        });
    }
}
