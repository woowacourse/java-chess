package chess.dto.responsedto;

import chess.dto.GridDto;
import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.List;

public class GridAndPiecesResponseDto implements ResponseDto {
    private final GridDto gridDto;
    private final List<PieceDto> piecesResponseDto;

    public GridAndPiecesResponseDto(GridDto gridDto, List<PieceDto> piecesResponseDto) {
        this.gridDto = gridDto;
        this.piecesResponseDto = new ArrayList<>(piecesResponseDto);
    }
}
