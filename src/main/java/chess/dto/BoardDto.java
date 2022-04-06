package chess.dto;

import java.util.List;

public class BoardDto {
    private final List<PieceDTO> pieces;
    private final String team;

    public BoardDto(List<PieceDTO> pieces, String team) {
        this.pieces = pieces;
        this.team = team;
    }
}
