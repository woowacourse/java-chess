package chess.dao;

import chess.Member;
import chess.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private final ConnectionManager connectionManager;

    public MemberDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<Member> getAllByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            List<Member> members = new ArrayList<>();
            String sql = "select * from member where board_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                members.add(
                        new Member(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("board_id")
                        ));
            }
            return members;
        });
    }
}
