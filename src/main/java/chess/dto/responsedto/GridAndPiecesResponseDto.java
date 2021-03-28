package chess.dto.responsedto;

import java.util.ArrayList;
import java.util.List;

public class GridAndPiecesResponseDto implements ResponseDto {
    private final GridResponseDto gridResponseDto;
    private final List<PieceResponseDto> piecesResponseDto;

    public GridAndPiecesResponseDto(GridResponseDto gridResponseDto, List<PieceResponseDto> piecesResponseDto) {
        this.gridResponseDto = gridResponseDto;
        this.piecesResponseDto = new ArrayList<>(piecesResponseDto);
    }
}
