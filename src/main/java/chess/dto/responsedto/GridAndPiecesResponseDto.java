package chess.dto.responsedto;

import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.List;

public class GridAndPiecesResponseDto implements ResponseDto {
    private final GridResponseDto gridResponseDto;
    private final List<PieceDto> piecesResponseDto;

    public GridAndPiecesResponseDto(GridResponseDto gridResponseDto, List<PieceDto> piecesResponseDto) {
        this.gridResponseDto = gridResponseDto;
        this.piecesResponseDto = new ArrayList<>(piecesResponseDto);
    }
}
