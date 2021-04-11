package chess.domain.chess;

import java.util.List;

import chess.domain.piece.PieceDTO;

public class ChessDTO {
    private final String status;
    private final String turn;
    private final List<PieceDTO> pieceDTOS;

    public ChessDTO(String status, String turn, List<PieceDTO> pieceDTOS) {
        this.status = status;
        this.turn = turn;
        this.pieceDTOS = pieceDTOS;
    }

    public String getStatus() {
        return status;
    }

    public String getTurn() {
        return turn;
    }

    public List<PieceDTO> getPieceDTOS() {
        return pieceDTOS;
    }
}
