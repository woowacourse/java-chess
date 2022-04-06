package chess.dao;

import java.util.List;

import chess.domain.Color;
import chess.domain.game.GameState;

public class GameDao {

    public List<String> readStateAndColor(String roomName) {
        String sql = "select * from game where room_name = ?";
        JdbcConnector.ResultSetHolder holder = JdbcConnector.query(sql)
            .parameters(roomName)
            .executeQuery();
        if (holder.next()) {
            return holder.getStrings("state", "turn_color");
        }
        return null;
    }

    public void saveGame(String state, Color color, String roomName) {
        JdbcConnector.query("insert into game(room_name, turn_color, state) values (?, ?, ?)")
            .parameters(roomName, color.name(), state)
            .executeUpdate();
    }

    public void updateState(GameState state, String roomName) {
        JdbcConnector.query("UPDATE game SET turn_color = ?, state = ? WHERE room_name = ?")
            .parameters(state.getColor().name(), state.getState(), roomName)
            .executeUpdate();
    }
}
