package chess.model.gameCreator;

import chess.model.board.Tile;

import java.util.Map;

@FunctionalInterface
public interface CreateStrategy {
    Map<String, Tile> create();
}
