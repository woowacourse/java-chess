package chess.dto;

import java.util.List;

public class BoardDto {
    private final List<PieceDto> pieces;
    private final String team;

    public BoardDto(List<PieceDto> pieces, String team) {
        this.pieces = pieces;
        this.team = team;
    }
}
