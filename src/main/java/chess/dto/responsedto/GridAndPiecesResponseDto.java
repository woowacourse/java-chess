package chess.dto.responsedto;

import java.util.List;

public class GridAndPiecesResponseDto implements ResponseDto {
    private final GridResponseDto grid;
    private final List<PieceResponseDto> pieces;

    public GridAndPiecesResponseDto(GridResponseDto grid, List<PieceResponseDto> pieces) {
        this.grid = grid;
        this.pieces = pieces;
    }
}
