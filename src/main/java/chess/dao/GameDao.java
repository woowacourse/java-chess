package chess.dao;

import java.util.List;

import chess.domain.Color;
import chess.domain.game.GameState;

public interface GameDao {
    List<String> readStateAndColor(String roomName);

    void saveGame(String state, Color color, String roomName);

    void updateState(GameState state, String roomName);
}
