package chess.dao;

import java.util.List;

public interface GameDao {
    List<String> readStateAndColor(String roomName);

    void saveGame(String state, String color, String roomName);

    void updateState(String state, String color, String roomName);
}
