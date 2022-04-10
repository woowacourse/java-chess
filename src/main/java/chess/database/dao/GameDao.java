package chess.database.dao;

import java.util.List;

import chess.database.dto.GameStateDto;

public interface GameDao {
    List<String> readStateAndColor(String roomName);

    void saveGame(GameStateDto gameStateDto, String roomName);

    void updateState(GameStateDto gameStateDto, String roomName);
}
