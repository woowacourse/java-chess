package chess.controller.web.dto;

import chess.domain.location.Position;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {
    private final Map<PositionDto, PieceDto> maps;

    public BoardDto(Map<Position, Piece> board) {
        maps = new HashMap<>();
        for (Position position : board.keySet()) {
            maps.put(new PositionDto(position), new PieceDto(board.get(position)));
        }
    }

    public Map<PositionDto, PieceDto> getMaps() {
        return maps;
    }
}
