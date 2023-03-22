package chess.controller.dto;

import java.util.Collections;
import java.util.Map;

public final class ChessBoardDto {

    private final Map<PositionDto, PieceDto> board;

    public ChessBoardDto(final Map<PositionDto, PieceDto> board) {
        this.board = board;
    }

    public Map<PositionDto, PieceDto> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
