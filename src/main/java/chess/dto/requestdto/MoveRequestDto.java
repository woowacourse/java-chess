package chess.dto.requestdto;

import chess.dto.PieceDto;

import java.util.List;

public class MoveRequestDto {
    private final List<PieceDto> piecesDto;

    public MoveRequestDto(List<PieceDto> piecesDto) {
        this.piecesDto = piecesDto;
    }
}
