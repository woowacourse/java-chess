package chess.controller.dto;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.*;
import java.util.stream.Collectors;

public class TileDto {
    private final List<Tile> tiles;

    public TileDto(Board board) {
        this.tiles = toDto(board);
    }

    private List<Tile> toDto(Board board) {
        Map<Position, Piece> result = board.get();
        List<Position> positions = Positions.getPositions();

        Map<Position, Piece> resultBoard = new LinkedHashMap<>();
        for (int i = 0; i < positions.size(); i++) {
            resultBoard.put(positions.get(i), result.get(positions.get(i)));
        }

        List<Tile> boardResult = new ArrayList<>(resultBoard.entrySet()
                .stream()
                .map(entry -> new Tile(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));

        return boardResult;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}

