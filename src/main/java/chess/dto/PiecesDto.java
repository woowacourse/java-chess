package chess.dto;

import java.util.List;

public class PiecesDto {
    private List<PieceDto> pieceDtoList;

    public PiecesDto(List<PieceDto> pieceDtoList) {
        this.pieceDtoList = pieceDtoList;
    }

    public List<PieceDto> getPieceDtoList() {
        return pieceDtoList;
    }

    public void setPieceDtoList(List<PieceDto> pieceDtoList) {
        this.pieceDtoList = pieceDtoList;
    }
}
