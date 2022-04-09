package chess.service.dto;

import java.util.List;
import java.util.Map;

public class BoardDto {
    private final List<PieceWithSquareDto> pieces;
    private int id;

    public BoardDto(List<PieceWithSquareDto> pieces) {
        this.pieces = pieces;
    }

    public BoardDto(int id, List<PieceWithSquareDto> pieces) {
        this.id = id;
        this.pieces = pieces;
    }

    public int getId() {
        return id;
    }

    public List<PieceWithSquareDto> getPieces() {
        return pieces;
    }
}
