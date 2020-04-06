package chess.controller.dto;

import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private final List<TileDto> tiles = new ArrayList<>();

    public ChessBoardDto(Map<Position, Piece> chessBoard) {
        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            tiles.add(new TileDto(entry.getKey(), entry.getValue()));
        }
    }

    public List<TileDto> getTiles() {
        return tiles;
    }
}
