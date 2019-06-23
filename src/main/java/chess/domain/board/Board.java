package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {
    private final Map<Tile, Optional<Piece>> board;

    public Board(Map<Tile, Piece> boardState) {
        board = new HashMap<>();
        Tile.stream()
                .forEach(tile ->
                        board.put(tile, getState(boardState, tile))
                );
    }

    private Optional<Piece> getState(Map<Tile, Piece> board, Tile tile) {
        if (board.keySet().contains(tile)) {
            return Optional.of(board.get(tile));
        }
        return Optional.empty();
    }

    public Optional<Piece> at(String tileText) {
        Tile tile = Tile.of(tileText);
        return board.get(tile);
    }
}
