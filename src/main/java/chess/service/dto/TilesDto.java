package chess.service.dto;

import chess.manager.ChessManager;

import java.util.List;
import java.util.stream.Collectors;

public class TilesDto {
    private final List<TileDto> tiles;

    public TilesDto(ChessManager chessManager) {
        this.tiles = chessManager.getChessBoard().values().stream()
                .map(TileDto::new)
                .collect(Collectors.toList());
    }

    public List<TileDto> getTiles() {
        return tiles;
    }

}
