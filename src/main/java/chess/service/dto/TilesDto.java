package chess.service.dto;

import chess.domain.board.ChessBoard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TilesDto {
    private final List<TileDto> tiles;

    public TilesDto(final ChessBoard chessBoard) {
        this.tiles = chessBoard.boards()
                .values()
                .stream()
                .map(TileDto::new)
                .collect(Collectors.toList());
        Collections.reverse(tiles);
    }

    public List<TileDto> getTiles() {
        return tiles;
    }
}
