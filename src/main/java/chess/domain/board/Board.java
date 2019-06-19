package chess.domain.board;

import chess.domain.ChessPiece;
import chess.domain.Tile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    private final Map<Tile, Optional<ChessPiece>> board;

    public Board(Map<Tile, ChessPiece> boardState) {
        board = new HashMap<>();
        Tile.stream()
                .forEach(tile ->
                        board.put(tile, getState(boardState, tile))
                );
    }

    private Optional<ChessPiece> getState(Map<Tile, ChessPiece> board, Tile tile) {
        if (board.keySet().contains(tile)) {
            return Optional.of(board.get(tile));
        }
        return Optional.empty();
    }

    public Optional<ChessPiece> at(String tileText) {
        Tile tile = Tile.of(tileText);
        return board.get(tile);
    }
}
