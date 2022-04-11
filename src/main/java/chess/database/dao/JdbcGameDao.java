package chess.database.dao;

import java.util.List;

import chess.database.dto.GameStateDto;

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
    public void saveGame(GameStateDto gameStateDto, String roomName) {
        JdbcConnector.query("insert into game(room_name, turn_color, state) values (?, ?, ?)")
            .parameters(roomName, gameStateDto.getTurnColor(), gameStateDto.getState())
            .executeUpdate();
    }

    @Override
    public void updateState(GameStateDto gameStateDto, String roomName) {
        JdbcConnector.query("UPDATE game SET state = ?, turn_color = ? WHERE room_name = ?")
            .parameters(gameStateDto.getState(), gameStateDto.getTurnColor(), roomName)
            .executeUpdate();
    }
}
