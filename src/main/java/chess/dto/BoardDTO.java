package chess.dto;

import chess.domain.location.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDTO;
import chess.dto.PositionDTO;

import java.util.HashMap;
import java.util.Map;

public class BoardDTO {
    private final Map<PositionDTO, PieceDTO> maps;

    public BoardDTO(Map<Position, Piece> board) {
        maps = new HashMap<>();
        for (Position position : board.keySet()) {
            maps.put(new PositionDTO(position), new PieceDTO(board.get(position)));
        }
    }

    public Map<PositionDTO, PieceDTO> getMaps() {
        return maps;
    }
}
