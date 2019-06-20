package chess.domain.chessround.dto;

import java.util.List;

public class ChessPlayerDTO {
    private List<ChessPieceDTO> chessPieceDTOs;

    public ChessPlayerDTO(List<ChessPieceDTO> chessPieceDTOs) {
        this.chessPieceDTOs = chessPieceDTOs;
    }

    public List<ChessPieceDTO> getChessPieceDTOs() {
        return chessPieceDTOs;
    }
}
