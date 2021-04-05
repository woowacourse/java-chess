package chess.dto.board;

import java.util.List;

public class RowDTO {

    private final List<PieceDTO> pieces;

    public RowDTO(List<PieceDTO> pieces) {
        this.pieces = pieces;
    }

    public List<PieceDTO> getPieces() {
        return pieces;
    }
}
