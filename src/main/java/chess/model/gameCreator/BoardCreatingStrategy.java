package chess.model.gameCreator;

import chess.model.board.Tile;

import java.util.Map;

@FunctionalInterface
public interface BoardCreatingStrategy {
    Map<String, Tile> create();
}
