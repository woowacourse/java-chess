package chess.dao;

import java.util.List;

public class JdbcGameDao implements GameDao {

    @Override
    public List<String> readStateAndColor(String roomName) {
        String sql = "select * from game where room_name = ?";
        JdbcConnector.ResultSetHolder holder = JdbcConnector.query(sql)
            .parameters(roomName)
            .executeQuery();
        if (holder.next()) {
            return holder.getStrings("state", "turn_color");
        }
        throw new IllegalArgumentException("[ERROR] 해당하는 게임이 없습니다.");
    }

    @Override
    public void saveGame(String state, String color, String roomName) {
        JdbcConnector.query("insert into game(room_name, turn_color, state) values (?, ?, ?)")
            .parameters(roomName, color, state)
            .executeUpdate();
    }

    @Override
    public void updateState(String state, String color, String roomName) {
        JdbcConnector.query("UPDATE game SET state = ?, turn_color = ? WHERE room_name = ?")
            .parameters(state, color, roomName)
            .executeUpdate();
    }
}
