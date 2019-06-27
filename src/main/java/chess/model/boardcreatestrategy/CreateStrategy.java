package chess.model.boardcreatestrategy;

import chess.model.board.Tile;

import java.util.Map;

@FunctionalInterface
public interface CreateStrategy {
    Map<String, Tile> create();
}
