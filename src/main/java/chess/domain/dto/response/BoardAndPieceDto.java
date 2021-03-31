package chess.domain.dto.response;

import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import java.util.ArrayList;
import java.util.List;

public class BoardAndPieceDto implements ResponseDto {
    private final BoardDto boardDto;
    private final List<PieceDto> pieceDtos;

    public BoardAndPieceDto(final BoardDto boardDto, List<PieceDto> pieceDtos) {
        this.boardDto = boardDto;
        this.pieceDtos = new ArrayList<>(pieceDtos);
    }
}
