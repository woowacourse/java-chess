package chess.dao;

import java.util.List;

import chess.database.GameStateDto;

public interface GameDao {
    List<String> readStateAndColor(String roomName);

    void saveGame(GameStateDto gameStateDto, String roomName);

    void updateState(GameStateDto gameStateDto, String roomName);
}
