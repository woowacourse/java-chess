package chess.dto;

import chess.domain.Team;

import java.util.List;

public class BoardDto {
    private final List<PieceDto> pieces;
    private final Team team;

    public BoardDto(List<PieceDto> pieces, Team team) {
        this.pieces = pieces;
        this.team = team;
    }
}
