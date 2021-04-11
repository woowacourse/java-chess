package chess.dto;

import java.util.List;

public class PiecesDTO {
    private List<PieceDTO> pieceDtoList;

    public PiecesDTO(List<PieceDTO> pieceDtoList) {
        this.pieceDtoList = pieceDtoList;
    }

    public List<PieceDTO> getPieceDtoList() {
        return pieceDtoList;
    }
}
